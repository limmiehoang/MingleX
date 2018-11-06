package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.User;
import java.util.List;
import java.util.Optional;

@Repository("chatroomRepository")
public interface ChatroomRepository extends JpaRepository<Chatroom, Long>, ChatroomRepositoryCustom {
	List<Chatroom> findByUser1AndUser2(User user1, User user2);

	Chatroom findById(int id);
}
