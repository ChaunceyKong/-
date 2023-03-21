package com.kong.controller;

import com.kong.mapper.UserMapper;
import com.kong.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();
        return userList;
    }

    @RequestMapping("/queryUserById")
    public User queryUserById() {
        User user = userMapper.queryUserById(2);
        return user;
    }

    @RequestMapping("/addUser")
    public String addUser() {
        userMapper.addUser(new User(5,"海绵宝宝","123456"));
        return "OK";
    }

    @RequestMapping("/updateUser")
    public String updateUser() {
        userMapper.updateUser(new User(5,"海绵宝宝","111111"));
        return "OK";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser() {
        userMapper.deleteUser(2);
        return "OK";
    }
}


