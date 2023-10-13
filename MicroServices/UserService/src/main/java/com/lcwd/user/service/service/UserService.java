package com.lcwd.user.service.service;

import com.lcwd.user.service.Entities.User;

import java.util.List;

public interface UserService {
    //create
    User saveUser(User user);
    //get All Users
    List<User> getAllUser();
    //get single User of given userId
    User getUser(String userId);
    //Assignment: 1. deleteUser  2. updateUser
}
