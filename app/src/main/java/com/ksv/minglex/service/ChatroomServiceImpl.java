package com.ksv.minglex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.User;
import com.ksv.minglex.repository.ChatroomRepository;

@Service("chatroomService")
public class ChatroomServiceImpl implements ChatroomService {

	@Autowired
	ChatroomRepository chatroomRepository;

	@Override
	public Chatroom save(Chatroom chatroom) {
		return chatroomRepository.save(chatroom);
	}

	@Override
	public Chatroom findByUser1AndUser2(User user1, User user2) {
		List<Chatroom> list = chatroomRepository.findByUser1AndUser2Custom(user1, user2);
		if (list != null && list.isEmpty() == false)
			return list.get(0);
		return null;
	}

	@Override
	public boolean checkMember(User curUser, Chatroom chatroom) {
		Chatroom chatroomDB = chatroomRepository.findById(chatroom.getId());
		if (chatroomDB == null) return false;
		return (chatroomDB.getUser1().getId() == curUser.getId() || chatroomDB.getUser2().getId() == curUser.getId());
	}

}
