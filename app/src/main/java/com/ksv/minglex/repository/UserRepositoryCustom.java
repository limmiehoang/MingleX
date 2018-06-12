package com.ksv.minglex.repository;

import com.ksv.minglex.model.User;

public interface UserRepositoryCustom {
	User findByUsernameCustom(String username);
	User findByUsernameAndPasswordCustom(String username, String password);
}
