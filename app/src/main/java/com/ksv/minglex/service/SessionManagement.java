package com.ksv.minglex.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Service;
@Service("sessionManagementService")
public class SessionManagement implements HttpSessionListener {
	private static List<String> sessions = new ArrayList<>();
	public static final String COUNTER = "session-counter";

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionCreated");
		System.out.println("getActiveSessionNumber: " + getActiveSessionNumber());
		HttpSession session = event.getSession();
		sessions.add(session.getId());
		session.setAttribute(SessionManagement.COUNTER, this);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("SessionCounter.sessionDestroyed");
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
		session.setAttribute(SessionManagement.COUNTER, this);
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}
}
