package com.kong.controller;

import com.kong.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    //http://localhost:8080/user/t1?name=kong
    @GetMapping("/t1")
    public String test1(@RequestParam("name") String name, Model model) {
        //1.接收前端参数
        System.out.println("接收到前端参数为："+name);

        //2.将返回结果传递给前端
        model.addAttribute("msg", name);

        //3.视图跳转
        return "test";
    }

    //从前端接收一个对象：id name age
    @GetMapping("/t2")
    public String test2(User user) {
        System.out.println(user);
        return "test";
    }
}
