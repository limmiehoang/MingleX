package com.ksv.minglex.service;

import com.ksv.minglex.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MatchServiceImpl implements MatchService {

    @Autowired
    UserService userService;

    @Override
    public List<User> findUsersByKeywordsAndGender(String keywords, String gender) {
        return userService.findUsersByKeywordsAndGender(keywords, gender);
    }
}
