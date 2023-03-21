package com.kong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kong.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/j1")
    @ResponseBody  //他就不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        //jackson
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("孔",1,"男");

        String str = mapper.writeValueAsString(user);

        return str;
    }

    @RequestMapping("/j2")
    @ResponseBody  //他就不会走视图解析器，会直接返回一个字符串
    public String json2() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user1 = new User("孔1",1,"男");
        User user2 = new User("孔2",1,"男");
        User user3 = new User("孔3",1,"男");
        User user4 = new User("孔4",1,"男");

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return  mapper.writeValueAsString(userList);
    }

    @RequestMapping("/j3")
    @ResponseBody  //他就不会走视图解析器，会直接返回一个字符串
    public String json3() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        //使用 ObjectMapper 来格式化
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //自定义日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        mapper.setDateFormat(simpleDateFormat);

        Date date = new Date();

        return  mapper.writeValueAsString(date);
    }
}
