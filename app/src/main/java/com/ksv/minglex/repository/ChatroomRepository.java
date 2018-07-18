package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Chatroom;

@Repository("chatroomRepository")
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
	
}
