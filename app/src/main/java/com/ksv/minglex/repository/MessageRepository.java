package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Message;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long>{
	
}
