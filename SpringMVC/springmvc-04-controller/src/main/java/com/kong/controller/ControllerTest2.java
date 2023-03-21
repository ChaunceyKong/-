package com.kong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller //代表这个类会被Spring接管
public class ControllerTest2{

    @RequestMapping("/t2")
    public String test1(Model model) {
        model.addAttribute("msg","ControllerTest2");
        return "test"; //WEB-INF/jsp/test.jsp
    }
}
