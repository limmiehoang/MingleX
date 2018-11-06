package com.ksv.minglex.controller;

import com.ksv.minglex.model.User;
import com.ksv.minglex.service.ParsingXmlService;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.UserService;
import com.ksv.minglex.storage.StorageFileNotFoundException;
import com.ksv.minglex.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
