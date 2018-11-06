package com.ksv.minglex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User findById(int id);

}
