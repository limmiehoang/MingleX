package com.ksv.minglex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.Message;
import com.ksv.minglex.repository.MessageRepository;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public List<Message> findByChatroom(Chatroom chatroom) {
		return messageRepository.findByChatroom(chatroom);
	}

	@Override
	public Message save(Message messsage) {
		return messageRepository.save(messsage);
	}

}
