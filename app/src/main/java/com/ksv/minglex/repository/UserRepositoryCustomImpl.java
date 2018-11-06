package com.ksv.minglex.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ksv.minglex.model.Chatroom;
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

    @Override
    public List<User> findUsersByKeywordsAndGender(String keywords, String gender) {
        // prepare for LIKE statement
        keywords = "%" + keywords.trim() + "%";

        Query q = em.createNativeQuery("SELECT * FROM user WHERE username LIKE :username AND gender = :gender", User.class);
        q.setParameter("username", keywords);
        q.setParameter("gender", gender);

        List<User> users = (List<User>) q.getResultList();

        return (users.size() > 0) ? users : null;
    }

    @Override
    public List<User> findUsersByKeywords(String keywords) {
        // prepare for LIKE statement
        keywords = "%" + keywords.trim() + "%";

        Query q = em.createNativeQuery("SELECT * FROM user WHERE username LIKE :username", User.class);
        q.setParameter("username", keywords);

        List<User> users = (List<User>) q.getResultList();

        return (users.size() > 0) ? users : null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllExceptMe(User user) {
		Query q = em.createNativeQuery("SELECT * FROM user WHERE NOT user_id=?", User.class);
		q.setParameter(1, user.getId());
		return (List<User>) q.getResultList();
	}


}
