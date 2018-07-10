package com.ksv.minglex.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksv.minglex.model.User;
import com.ksv.minglex.setting.SecuritySetting;

@Service("sessionService")
public class SessionService {
	@Autowired
	SecuritySetting securitySetting;
	
	public User getCurrentUser(HttpServletRequest request) {
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("user");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public void setCurrentUser(HttpServletRequest request, User user) {
		user.setPassword(null);
		request.getSession().setAttribute("user", user);
	}

	public void removeCurrentSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}

	public void refreshSessionID(HttpServletRequest request) {
		// To obtain existing session and not create a new one
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// Will create a new session in case it doesn’t exist
		request.getSession(true);
	}

	/*
	 * Remove cookie (client side)
	 */
	public void eraseCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
	}

	public void sessionFixation(HttpServletRequest request) {
		if (securitySetting.getSessionFixation()) {
			refreshSessionID(request);
		}
	}
}
