package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.LevelEnum;

import java.util.List;

public interface IUserService {
    User create(User user);
    List<User> findAll();
    User getByUsername(String username);
    void addUserSport(Integer userId, Integer sportId, boolean favorite, LevelEnum level);
    void removeUserSport(Integer userId, Integer sportId);
}
