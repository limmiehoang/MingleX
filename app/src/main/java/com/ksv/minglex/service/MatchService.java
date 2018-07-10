package com.ksv.minglex.service;

import com.ksv.minglex.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("matchService")
public interface MatchService {
    public List<User> findUsersByKeywordsAndGender(String keywords, String gender);

}
