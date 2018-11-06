package com.ksv.minglex.controller;

import com.ksv.minglex.model.User;
import com.ksv.minglex.service.SessionService;
import com.ksv.minglex.service.StatusService;
import com.ksv.minglex.service.UserService;
import com.ksv.minglex.setting.SecuritySetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MatchController {

    @Autowired
    StatusService statusService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    SecuritySetting securitySetting;

    private ModelAndView modelAndView;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        modelAndView = new ModelAndView();

        User user = sessionService.getCurrentUser(request);
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        String keywords = request.getParameter("keywords");
        String gender = request.getParameter("gender");

        if (keywords == null || keywords.length() == 0) {
            keywords = "";
        }
        if (gender == null || gender.length() == 0) {
            gender = "all";
        }

        System.out.println("Searching for: keywords: " + keywords + ", gender: " + gender);

        List<User> users = userService.findUsersByKeywordsAndGender(keywords, gender);

        modelAndView.addObject("curUser", user);
        modelAndView.addObject("users", users);
        modelAndView.addObject("keywords", keywords);
        modelAndView.addObject("gender", gender);
        modelAndView.addObject("reflectedXss", securitySetting.getReflectedXSS());

        modelAndView.setViewName("search");
        return modelAndView;
    }
}
