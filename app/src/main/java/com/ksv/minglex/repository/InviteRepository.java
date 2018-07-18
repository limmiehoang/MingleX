package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

}
