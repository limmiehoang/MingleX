package com.ksv.minglex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.User;
import com.ksv.minglex.service.InviteService;
import com.ksv.minglex.service.SessionService;

@RestController
public class ChatRestController {

	@Autowired
	InviteService inviteService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "/room/invite", method = RequestMethod.POST)
	public ResponseEntity<Invite> createInvite(@RequestBody Invite invite, HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<Invite>(HttpStatus.FOUND);
		}
		invite.setSender(curUser);
		if (inviteService.saveInvite(invite) != null) {
			return new ResponseEntity<Invite>(HttpStatus.OK);
		}

		return null;
	}
}
