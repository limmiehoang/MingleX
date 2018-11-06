package com.ksv.minglex.setting;

import java.util.List;

import com.ksv.minglex.model.Chatroom;
import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.Message;

public class ChatroomResJSONObj {
	private String status;
	private List<Message> messages;
	private Chatroom chatroom;
	private Invite invite;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Invite getInvite() {
		return invite;
	}

	public void setInvite(Invite invite) {
		this.invite = invite;
	}

	public Chatroom getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

}
