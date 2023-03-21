package com.kong.service;

import com.kong.dao.UserDao;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    //利用set实现值的动态注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
