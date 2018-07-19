package com.ksv.minglex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksv.minglex.model.User;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "/profile", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody User user, HttpServletRequest request) {
		User curUser = sessionService.getCurrentUser(request);
		if (curUser == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		user.setId(curUser.getId());
		User resUser = userService.updateUser(user);
		sessionService.setCurrentUser(request, resUser);
		if (resUser != null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
