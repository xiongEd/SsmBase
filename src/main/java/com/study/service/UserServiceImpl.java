package com.study.service;

import javax.annotation.Resource;

import com.study.mapper.UserMapper;
import com.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserServiceInf {
    @Resource
    private UserMapper userDao;


    public User getUserById(int userId) {
        // TODO Auto-generated method stub
        return this.userDao.selectByPrimaryKey(userId);
    }

    public int insert(User user) {
        // TODO Auto-generated method stub
        return this.userDao.insert(user);
    }
}

