package com.ksv.minglex.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ksv.minglex.model.User;

@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUsernameCustom(String username) {
		Query q = em.createNativeQuery("SELECT * FROM user WHERE username='" + username + "'", User.class);
		List<User> users = (List<User>) q.getResultList();
		if (users.size() == 0) return null;
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsernameAndPasswordCustom(String username, String password) {
		Query q = em.createNativeQuery("SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'", User.class);
		List<User> users = (List<User>) q.getResultList();
		if (users.size() == 0) return null;
		return users.get(0);
	}
	
}
