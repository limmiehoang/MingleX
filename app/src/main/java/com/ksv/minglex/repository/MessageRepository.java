package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Message;
import com.ksv.minglex.model.Chatroom;
import java.util.List;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByChatroom(Chatroom chatroom);
}
