package com.ksv.minglex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.Message;
import com.ksv.minglex.model.User;
import com.ksv.minglex.service.ChatroomService;
import com.ksv.minglex.service.InviteService;
import com.ksv.minglex.service.MessageService;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.setting.AcceptReqJSONObj;
import com.ksv.minglex.setting.ChatroomResJSONObj;

@RestController
public class ChatRestController {

	@Autowired
	InviteService inviteService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/room/invite", method = RequestMethod.POST)
	public ResponseEntity<ChatroomResJSONObj> createInvite(@RequestBody Invite invite, HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.UNAUTHORIZED);
		}
		invite.setSender(curUser);
		ChatroomResJSONObj resObj = new ChatroomResJSONObj();
		resObj.setStatus("waiting");
		if (inviteService.saveInvite(invite) != null) {
			return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
		}
		return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/room/accept", method = RequestMethod.POST)
	public ResponseEntity<ChatroomResJSONObj> acceptInvite(@RequestBody AcceptReqJSONObj res,
			HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.FOUND);
		}
		Invite invite = inviteService.findInviteBySenderAndRecipientAndStatus(res.getChatmate(), curUser, "invited");
		if (invite != null) {
			// Update invite's status to accepted, add feedback to invite
			invite.setStatus("accepted");
			invite.setFeedback(res.getInvite().getFeedback());
			invite = inviteService.updateInvite(invite);
			// Create room
			Chatroom chatroom = new Chatroom();
			chatroom.setUser1(res.getChatmate());
			chatroom.setUser2(curUser);
			chatroomService.save(chatroom);
			chatroom = chatroomService.findByUser1AndUser2(res.getChatmate(), curUser);

			// Response connected and key exchange infomation
			ChatroomResJSONObj resObj = new ChatroomResJSONObj();
			resObj.setStatus("connected");
			resObj.setChatroom(chatroom);
			resObj.setInvite(invite);
			return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
		}

		return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/room/chatmate", method = RequestMethod.GET)
	public ResponseEntity<ChatroomResJSONObj> getRoomInfo(@RequestParam int chatmateId, HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.UNAUTHORIZED);
		}

		// Response object to exchange infomation
		ChatroomResJSONObj resObj = new ChatroomResJSONObj();
		Invite invite = null;
		User chatmate = new User();
		chatmate.setId(chatmateId);
		Chatroom chatroom = chatroomService.findByUser1AndUser2(curUser, chatmate);
		if (chatroom != null) {
			invite = inviteService.findInviteBySenderAndRecipient(chatmate, curUser);
			if (invite != null && ("accepted".equals(invite.getStatus()) || "connected".equals(invite.getStatus()))) {
				// La nguoi nhan thu moi
				// Status accepted -> da accepted va dang doi ng gui loi moi nhan OK
				// Status connected
				// return connected and messages
				resObj.setStatus("connected");
				resObj.setChatroom(chatroom);
				resObj.setMessages(messageService.findByChatroom(chatroom));

				return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			}
			invite = inviteService.findInviteBySenderAndRecipient(curUser, chatmate);
			if (invite != null && "accepted".equals(invite.getStatus())) {
				// La nguoi gui thu moi, status accepted -> nguoi dc moi accepted
				// return accepted
				resObj.setStatus("accepted");
				resObj.setInvite(invite);
				return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			}
			if (invite != null && "connected".equals(invite.getStatus())) {
				// La nguoi gui thu moi, status connected
				// return connected
				resObj.setStatus("connected");
				resObj.setChatroom(chatroom);
				resObj.setMessages(messageService.findByChatroom(chatroom));
				return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			}

		} else {
			invite = inviteService.findInviteBySenderAndRecipient(curUser, chatmate);
			if (invite != null && "invited".equals(invite.getStatus())) {
				// la nguoi gui thu moi, nhung chua nhan dc phan hoi, chua co room
				// return waiting
				resObj.setStatus("waiting");
				return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			}
			invite = inviteService.findInviteBySenderAndRecipient(chatmate, curUser);
			if (invite != null && "invited".equals(invite.getStatus())) {
				// la nguoi nhan thu moi, nhung chua tra loi, chua co room
				// return invited
				resObj.setStatus("invited");
				resObj.setInvite(invite);
				return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			}
			resObj.setStatus("unconnected");
			return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
			// return unconnected
		}
		return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/room/connect", method = RequestMethod.POST)
	public ResponseEntity<ChatroomResJSONObj> completeInvite(@RequestBody AcceptReqJSONObj res,
			HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.FOUND);
		}
		Invite invite = inviteService.findInviteBySenderAndRecipientAndStatus(curUser, res.getChatmate(), "accepted");
		if (invite != null) {
			// Update invite's status to connected
			invite.setStatus("connected");
			invite = inviteService.updateInvite(invite);
			// Create room
			Chatroom chatroom = chatroomService.findByUser1AndUser2(curUser, res.getChatmate());

			// Response connected and key exchange infomation
			ChatroomResJSONObj resObj = new ChatroomResJSONObj();
			resObj.setMessages(messageService.findByChatroom(chatroom));
			resObj.setStatus("connected");
			resObj.setChatroom(chatroom);
			return new ResponseEntity<ChatroomResJSONObj>(resObj, HttpStatus.OK);
		}

		return new ResponseEntity<ChatroomResJSONObj>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/room/chat", method = RequestMethod.POST)
	public ResponseEntity<Message> sendMessage(@RequestBody Message msg, HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<Message>(HttpStatus.FOUND);
		}

		// Authenticate room's member
		if (chatroomService.checkMember(curUser, msg.getChatroom())) {
			msg.setSender(curUser);
			Message message = messageService.save(msg);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}

		return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
	}
}
