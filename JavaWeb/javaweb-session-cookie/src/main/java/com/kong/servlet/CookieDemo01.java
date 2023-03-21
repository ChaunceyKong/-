package com.kong.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器，告诉你来的时间，把这个时间封装成一个信件，你下次带来，我就知道你来了
        //解决中文乱码
        req.setCharacterEncoding("utf-16");
        resp.setCharacterEncoding("utf-16");

        PrintWriter out = resp.getWriter();

        //Cookie,服务器端从客户端获取
        Cookie[] cookies = req.getCookies(); //这里返回数组，说明Cookie可能有多个

        //判断cookie是否存在
        if (cookies != null) {
            //如果存在怎么办
            out.write("你上一次访问的时间是：");

            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")) {
                    //获取cookie的值
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        }
        else {
            out.write("这是您第一次访问本站");
        }

        //服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
        //cookie有效期为1天

        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
