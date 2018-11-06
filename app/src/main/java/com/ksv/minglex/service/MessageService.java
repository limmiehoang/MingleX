package com.ksv.minglex.service;

import java.util.List;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.Message;

public interface MessageService {
	public Message save(Message messsage);

	public List<Message> findByChatroom(Chatroom chatroom);
}
