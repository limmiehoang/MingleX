package com.ksv.minglex.service;

import com.ksv.minglex.model.User;

import java.util.List;

public interface UserService {
	public User findUserByUsername(String username);

	public void saveUser(User user);

	public User authenticateUser(User user);

	public User findUserById(String id);

	public List<User> findUsersByKeywordsAndGender(String keywords, String gender);

	public User updateUser(User user);

	public List<User> findAllExceptMe(User user);

}
