package com.ksv.minglex.service;

import com.ksv.minglex.model.User;

public interface UserService {
	public User findUserByUsername(String username);
	public void saveUser(User user);
	public User authenticateUser(User user);
}
