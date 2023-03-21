package com.kong.filter;

import com.kong.pojo.User;
import com.kong.util.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requset = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //过滤器，从Session中获取用户
        User user = (User) requset.getSession().getAttribute(Constants.USER_SESSION);

        if (user == null) { //已经被移除了，或者注销了，或者未登录
            response.sendRedirect("/smbms/error.jsp");
        }
        else {
            chain.doFilter(req,resp);
        }

    }

    public void destroy() {

    }
}
