package com.ksv.minglex.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.repository.InviteRepository;

@Service("inviteService")
public class InviteServiceImpl implements InviteService {

	@Autowired
	InviteRepository inviteRepository;

	@Override
	public Invite saveInvite(@Valid Invite invite) {
		// Check invite is exist
		if (inviteRepository.findBySenderAndRecipient(invite.getSender(), invite.getRecipient()) != null
				|| inviteRepository.findBySenderAndRecipient(invite.getSender(), invite.getSender()) != null) {
			return null;
		}
		return inviteRepository.save(invite);
	}

}
