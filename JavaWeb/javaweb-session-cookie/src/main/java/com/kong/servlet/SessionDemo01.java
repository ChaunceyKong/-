package com.kong.servlet;

import com.kong.pojo.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到Session
        HttpSession session = req.getSession();

        //给Session中存东西
        session.setAttribute("name",new Person("孔祥宇",1));

        //获取Session的id
        String sessionId = session.getId();
        //判断Session是不是新创建的
        if (session.isNew()) {
            resp.getWriter().write("session创建成功，Id："+sessionId);
        }
        else {
            resp.getWriter().write("session已经存在，Id："+sessionId);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
