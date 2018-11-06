package com.ksv.minglex.setting;

import com.ksv.minglex.model.Invite;
import com.ksv.minglex.model.User;

public class AcceptReqJSONObj {
	private User chatmate;
	private Invite invite;

	public User getChatmate() {
		return chatmate;
	}

	public void setChatmate(User chatmate) {
		this.chatmate = chatmate;
	}

	public Invite getInvite() {
		return invite;
	}

	public void setInvite(Invite invite) {
		this.invite = invite;
	}
}
