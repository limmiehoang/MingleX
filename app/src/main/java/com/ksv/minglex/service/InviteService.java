package com.ksv.minglex.service;

import java.util.List;

import javax.validation.Valid;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.User;

public interface InviteService {

	Invite saveInvite(@Valid Invite invite);

	boolean checkInvite(User curUser, User chatmate);

	Invite findInviteBySenderAndRecipient(User curUser, User chatmate);

	List<Invite> findInvitesBySenderAndRecipient(User curUser, User chatmate);

	Invite findInviteBySenderAndRecipientAndStatus(User curUser, User chatmate, String status);

	Invite updateInvite(Invite invite);

}
