package com.kong.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.kong.pojo.Role;
import com.kong.pojo.User;
import com.kong.service.role.RoleService;
import com.kong.service.role.RoleServiceImpl;
import com.kong.service.user.UserService;
import com.kong.service.user.UserServiceImpl;
import com.kong.util.Constants;
import com.kong.util.PageSupport;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

// 实现Servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method != null && method.equals("savepwd")) {
            this.updatePwd(req, resp);
        }
        else if (method != null && method.equals("pwdmodify")) {
            this.pwdModify(req,resp);
        }
        else if (method != null && method.equals("query")) {
            this.query(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        //从session里面拿id
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        boolean flag = false;

        if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getId(), newpassword);
            if (flag) {
                req.setAttribute("message","修改密码成功，请重新登陆！");
                //密码修改成功，移除当前session
                req.getSession().removeAttribute(Constants.USER_SESSION);
                //resp.sendRedirect("/smbms/error.jsp"); //返回错误页面
            }
            else { //密码修改失败
                req.setAttribute("message","修改密码失败！");
            }
        }
        else { //密码修改失败
            req.setAttribute("message","新密码设置有问题！");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证旧密码,session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //万能的Map:结果集
        HashMap<String, String> resultMap = new HashMap<String, String>();

        if (o == null) { //session失效了，过期了
            resultMap.put("result","sessionerror");
        }
        else if (StringUtils.isNullOrEmpty(oldpassword)) { // 输入的密码为空
            resultMap.put("result","error");
        }
        else {
            String userPassword = ((User) o).getUserPassword(); //session中用户的密码
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result","true");
            }
            else {
                resultMap.put("result","false");
            }
        }


        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JSONArray 阿里巴巴的json工具类
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //重点、难点
    public void query(HttpServletRequest req, HttpServletResponse resp) {

        //查询用户列表
        //从前端获取数据
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole"); //临时的
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = null;

        //第一次走这个请求一定是第一页，页面大小固定
        int pageSize = 5; //可以把这个写到配置文件里，方便后面修改
        int currentPageNo = 1;

        if (queryUserName == null) {
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")) {
            queryUserRole = Integer.parseInt(temp); //给查询赋值
        }
        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        //获取用户总数
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);

        //总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();

        //控制首页和尾页
        if (totalPageCount < 1) { //如果页面小于1，就显示第一页的东西
            currentPageNo = 1;
        }
        else if (currentPageNo > totalPageCount) { //当前页面大于最后一页
            currentPageNo = totalPageCount;
        }

        //获取用户列表展示
        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo,pageSize);
        req.setAttribute("userList", userList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryUserRole",queryUserRole);

        //返回前端测试
        try {
            req.getRequestDispatcher("userlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
