package com.ksv.minglex.repository;

import com.ksv.minglex.model.User;

import java.util.List;

public interface UserRepositoryCustom {
	User findByUsernameCustom(String username);
	User findByUsernameAndPasswordCustom(String username, String password);
	List<User> findUsersByKeywordsAndGender(String keywords, String gender);
	List<User> findUsersByKeywords(String keywords);
	List<User> findAllExceptMe(User user);
}
