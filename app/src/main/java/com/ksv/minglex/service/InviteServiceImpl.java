package com.ksv.minglex.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.User;
import com.ksv.minglex.repository.InviteRepository;

@Service("inviteService")
public class InviteServiceImpl implements InviteService {

	@Autowired
	InviteRepository inviteRepository;

	@Override
	public Invite saveInvite(@Valid Invite invite) {
		// Validate input
		if (invite.getSender().getId() == invite.getRecipient().getId())
			return null;
		// Check invite is exist
		if (inviteRepository.findBySenderAndRecipient(invite.getSender(), invite.getRecipient()).isEmpty()
				&& inviteRepository.findBySenderAndRecipient(invite.getRecipient(), invite.getSender()).isEmpty()) {
			invite.setStatus("invited");
			return inviteRepository.save(invite);

		}
		return null;
	}

	@Override
	public boolean checkInvite(User curUser, User chatmate) {
		return !inviteRepository.findBySenderAndRecipient(curUser, chatmate).isEmpty();
	}

	@Override
	public Invite findInviteBySenderAndRecipient(User curUser, User chatmate) {
		List<Invite> listInvite = inviteRepository.findBySenderAndRecipient(curUser, chatmate);
		if (listInvite != null && listInvite.isEmpty() == false) {
			return listInvite.get(0);
		}
		return null;
	}

	@Override
	public List<Invite> findInvitesBySenderAndRecipient(User curUser, User chatmate) {
		return inviteRepository.findBySenderAndRecipient(curUser, chatmate);
	}

	@Override
	public Invite findInviteBySenderAndRecipientAndStatus(User curUser, User chatmate, String status) {
		List<Invite> listInvite = inviteRepository.findInviteBySenderAndRecipientAndStatus(curUser, chatmate, status);
		if (listInvite != null && listInvite.isEmpty() == false) {
			return listInvite.get(0);
		}
		return null;
	}

	@Override
	public Invite updateInvite(Invite invite) {
		inviteRepository.save(invite);
		return invite;
	}
}
