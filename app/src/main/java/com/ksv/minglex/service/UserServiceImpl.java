package com.ksv.minglex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.User;
import com.ksv.minglex.repository.UserRepository;
import com.ksv.minglex.setting.SecuritySetting;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SecuritySetting securitySetting;
	@Autowired
	private PlainTextPasswordEncoder plainTextPasswordEncoder;
	@Autowired
	private SHA256PasswordEncoder sha256PasswordEncoder;
	@Autowired
	private SaltSHA256PasswordEncoder saltSHA256PasswordEncoder;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		String password = "";
		System.out.println(securitySetting.getStorePasswordSolution() + "123123");
		switch (securitySetting.getStorePasswordSolution()) {
		case "Hash":
			password = sha256PasswordEncoder.encode(user.getPassword());
			break;
		case "SaltHash":
			password = saltSHA256PasswordEncoder.encode(user.getPassword());
			break;
		case "BCrypt":
			password = bCryptPasswordEncoder.encode(user.getPassword());
			break;
		default:
			password = plainTextPasswordEncoder.encode(user.getPassword());
		}
		user.setPassword(password);
		userRepository.save(user);
	}

	@Override
	public User authenticateUser(User user) {
		User userdb;

		if ("Plain".equals(securitySetting.getStorePasswordSolution())) {
			if (securitySetting.getSqlInjection()) {
				userdb = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			} else {
				userdb = userRepository.findByUsernameAndPasswordCustom(user.getUsername(), user.getPassword());
			}
		} else {
			if (securitySetting.getSqlInjection()) {
				userdb = userRepository.findByUsername(user.getUsername());
			} else {
				userdb = userRepository.findByUsernameCustom(user.getUsername());
			}
			if (userdb == null)
				return null; // username not found
			switch (securitySetting.getStorePasswordSolution()) {
				case "Hash":
					if (!sha256PasswordEncoder.matches(user.getPassword(), userdb.getPassword())) userdb = null;
					break;
				case "SaltHash":
					if (!saltSHA256PasswordEncoder.matches(user.getPassword(), userdb.getPassword())) userdb = null;
					break;
				case "BCrypt":
					if (!bCryptPasswordEncoder.matches(user.getPassword(), userdb.getPassword())) userdb = null;
			}
		}
		return userdb;
	}

	@Override
	public User findUserById(String id) {
		return userRepository.findById(Integer.parseInt(id));
	}

	@Override
	public List<User> findUsersByKeywordsAndGender(String keywords, String gender) {
		// search users for all gender
		if (gender.equalsIgnoreCase("all")) {
			return userRepository.findUsersByKeywords(keywords);
		}
		return userRepository.findUsersByKeywordsAndGender(keywords, gender);
	}

	@Override
	public User updateUser(User user) {
		User userDb = userRepository.findById(user.getId());
		if (userDb == null)
			return null;
		String gender = user.getGender();
		String lookingfor = user.getLookingfor();
		String crushingOn = user.getCrushingOn();
		if (gender != null)
			userDb.setGender(gender);
		if (lookingfor != null)
			userDb.setLookingfor(lookingfor);
		if (crushingOn != null)
			userDb.setCrushingOn(crushingOn);
		userRepository.save(userDb);
		return userDb;
	}

	@Override
	public List<User> findAllExceptMe(User user) {
		return userRepository.findAllExceptMe(user);
	}

}
