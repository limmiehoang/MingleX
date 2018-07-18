package com.ksv.minglex.service;

import javax.validation.Valid;

import com.ksv.minglex.model.Invite;

public interface InviteService {

	Invite saveInvite(@Valid Invite invite);

}
