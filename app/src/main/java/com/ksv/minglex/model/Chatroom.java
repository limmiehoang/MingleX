package com.ksv.minglex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chatroom")
public class Chatroom {

	@Id
	@Column(name = "chatroom_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "user1_id", nullable = false)
	private User user1;

	@ManyToOne
	@JoinColumn(name = "user2_id", nullable = false)
	private User user2;

	@Column(name = "created_at")

	private String created_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
