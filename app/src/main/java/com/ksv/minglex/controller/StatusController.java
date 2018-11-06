package com.ksv.minglex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.ksv.minglex.setting.SecuritySetting;

@Controller
public class StatusController {

	@Autowired
	private StatusService statusService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private SecuritySetting securitySetting;

	@RequestMapping(value = "/explore", method = RequestMethod.GET)
	public ModelAndView explore(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user == null) {
			return new ModelAndView("redirect:/login");
		}
		List<Status> statuses = statusService.findAll();

		Status status = new Status();
		modelAndView.addObject("status", status);
		modelAndView.addObject("curUser", user);
		modelAndView.addObject("statuses", statuses);
		modelAndView.addObject("storedXss", securitySetting.getStoredXSS());
		modelAndView.setViewName("explore");
		return modelAndView;
	}

	@RequestMapping(value = "/status/new", method = RequestMethod.POST)
	public ModelAndView addStatus(@Valid Status status, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = sessionService.getCurrentUser(request);
		if (user == null) {
			return new ModelAndView("redirect:/login");
		}
		if (bindingResult.hasErrors()) {
			// TODO: Need redirect to previous page if have any errors (post status from
			// /explore or /profile).
			return new ModelAndView("redirect:/explore");
		} else {
			status.setUser(user);
			modelAndView.addObject("successMessage", "Status has been created successfully");
			modelAndView.addObject("status", status);
			System.out.println(status.toString());
			statusService.save(status);
			return new ModelAndView("redirect:/explore");
		}
	}
}
