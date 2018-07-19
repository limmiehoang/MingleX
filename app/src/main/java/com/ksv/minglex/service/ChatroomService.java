package com.ksv.minglex.service;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.User;

public interface ChatroomService {

	public Chatroom findByUser1AndUser2(User user1, User user2);

	public Chatroom save(Chatroom chatroom);

	public boolean checkMember(User curUser, Chatroom chatroom);

}
