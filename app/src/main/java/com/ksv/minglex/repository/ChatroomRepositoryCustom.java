package com.ksv.minglex.repository;

import java.util.List;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.User;

public interface ChatroomRepositoryCustom {
	public List<Chatroom> findByUser1AndUser2Custom(User user1, User user2);
}
