package com.ksv.minglex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksv.minglex.model.Status;
import com.ksv.minglex.model.User;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.StatusService;
import com.ksv.minglex.service.UserService;
import com.ksv.minglex.setting.SecuritySetting;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private SecuritySetting securitySetting;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user != null) {
			return new ModelAndView("redirect:/profile");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User curUser = new User();
		curUser.setUsername(request.getParameter("username"));
		curUser.setPassword(request.getParameter("password"));
		curUser.setGender(request.getParameter("gender"));

		// Validation
		if (curUser.getUsername() == null || curUser.getUsername().length() == 0) {
			modelAndView.addObject("errorMessage", "Username is required");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		if (curUser.getPassword() == null || curUser.getPassword().length() == 0) {
			modelAndView.addObject("errorMessage", "Password is required");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		// Authentication
		User resUser = userService.authenticateUser(curUser);
		if (resUser != null) {
			// Session fixation
			sessionService.sessionFixation(request);
			sessionService.setCurrentUser(request, resUser);
			return new ModelAndView("redirect:/profile");
		} else {
			// Has error
			modelAndView.addObject("errorMessage", "FAILED");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user",
					"There is already a user registered with the username provide");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			return new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profileView(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Status> statuses;
		String idStr = request.getParameter("id");

		// Redirect to /profile if this is current user
		if (Integer.toString(user.getId()).equals(idStr)) {
			return new ModelAndView("redirect:/profile");
		}

		if (idStr == null || idStr.length() == 0) {
			statuses = statusService.findByUser(Integer.toString(user.getId()));
		} else {
			statuses = statusService.findByUser(idStr);
			try {
				User otherUser = userService.findUserById(idStr);
				modelAndView.addObject("otherUser", otherUser);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Status status = new Status();
		modelAndView.addObject("status", status);
		modelAndView.addObject("curUser", user);
		modelAndView.addObject("statuses", statuses);
		modelAndView.addObject("storedXss", securitySetting.getStoredXSS());
		modelAndView.setViewName("profile");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		// Remove cookie (client side)
		sessionService.eraseCookie(request, response);
		sessionService.removeCurrentSession(request);
		return new ModelAndView("redirect:/login");
	}

}
