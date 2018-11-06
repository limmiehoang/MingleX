package com.ksv.minglex.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.User;

public class ChatroomRepositoryCustomImpl implements ChatroomRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Chatroom> findByUser1AndUser2Custom(User user1, User user2) {
		Query q = em.createNativeQuery(
				"SELECT chatroom_id, user1_id, user2_id, created_at FROM chatroom WHERE (user1_id=? AND user2_id=?) OR (user2_id=? AND user1_id=?)",
				Chatroom.class);
		q.setParameter(1, user1.getId());
		q.setParameter(2, user2.getId());
		q.setParameter(3, user1.getId());
		q.setParameter(4, user2.getId());
		List<Chatroom> chatrooms = (List<Chatroom>) q.getResultList();
		return chatrooms;
	}

}
