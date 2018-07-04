package com.ksv.minglex.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User authenticateUser(User user) {
		User userdb;

		switch (securitySetting.getStorePasswordSolution()) {
		case "SaltHash":
		case "BCrypt":
			if (securitySetting.getSqlInjection()) {
				userdb = userRepository.findByUsername(user.getUsername());
			} else {
				userdb = userRepository.findByUsernameCustom(user.getUsername());
			}
			if (userdb == null)
				return null; // username not found
			if (passwordEncoder.matches(user.getPassword(), userdb.getPassword())) {
				return userdb;
			} else {
				return null;
			}
		default:
			if (securitySetting.getSqlInjection()) {
				userdb = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			} else {
				userdb = userRepository.findByUsernameAndPasswordCustom(user.getUsername(), user.getPassword());
			}
		}
		return userdb;
	}

	@Override
	public User findUserById(String id) {
		return userRepository.findById(Integer.parseInt(id));
	}

}
