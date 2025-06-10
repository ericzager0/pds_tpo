package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.User;

import java.util.List;

public interface IUserService {
    User create(User user);
    List<User> findAll();
    User getByUsername(String username);
}
