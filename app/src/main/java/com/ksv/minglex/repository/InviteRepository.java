package com.ksv.minglex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.User;
import java.util.List;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
	<S extends Invite> S save(S entity);

	List<Invite> findBySenderAndRecipient(User sender, User recipient);

	List<Invite> findInviteBySenderAndRecipientAndStatus(User curUser, User chatmate, String status);
}
