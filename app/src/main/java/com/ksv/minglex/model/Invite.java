package com.ksv.minglex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "invite")
public class Invite {
	@Id
	@Column(name = "invite_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "recipient", nullable = false)
	private User recipient;
	
	@ManyToOne
	@JoinColumn(name = "sender", nullable = false)
	private User sender;
	
	@Column(name = "message", nullable = false)
	@NotEmpty(message = "*Please provide message")
	private String message;
	
	@Column(name = "feedback", nullable = true)
	private String feedback;
	
	@Column(name = "status", nullable = true, columnDefinition="varchar(20) default 'invited'")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
