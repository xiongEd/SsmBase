package com.study.service;

import com.study.model.User;

public interface UserServiceInf {
    public User getUserById(int userId);

    public int insert(User user);
}
