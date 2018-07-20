package com.ksv.minglex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksv.minglex.model.User;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.UserService;

@Controller
public class ChatController {
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView explore(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user == null) {
			return new ModelAndView("redirect:/login");
		}
		List<User> chatmates = userService.findAllExceptMe(user);

		modelAndView.addObject("curUser", user);
		modelAndView.addObject("chatmates", chatmates);
		modelAndView.setViewName("chat");
		return modelAndView;
	}
}
