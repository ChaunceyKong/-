package com.kong.servlet.user;

import com.kong.pojo.User;
import com.kong.service.user.UserService;
import com.kong.service.user.UserServiceImpl;
import com.kong.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //Servlet：控制层，调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start...");

        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库中的密码进行对比，调用业务层代码
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        if (user != null) { //查有此人，可以登录
            //将用户的信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到主页
            resp.sendRedirect("jsp/frame.jsp");
        }
        else { //查无此人
            //转发回登录页面，顺带提示他，用户名或密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
