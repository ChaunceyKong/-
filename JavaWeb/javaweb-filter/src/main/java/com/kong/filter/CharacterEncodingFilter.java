package com.kong.filter;


import jakarta.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    // 初始化：web服务器启动，他就已经初始化了，随时等待过滤对象！
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化了");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CharacterEncodingFilter执行前...");
        chain.doFilter(request,response);
        System.out.println("CharacterEncodingFilter执行后...");

    }
}