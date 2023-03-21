<%--
  Created by IntelliJ IDEA.
  User: xykong
  Date: 2022/4/16
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%--JSP表达式
  作用：用来将程序的输出，输出到客户端
  <%= 变量或者表达式%>
--%>
  <%= new java.util.Date()%>

  <hr>
  <%--jsp脚本片段--%>
  <%
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
      sum += i;
    }
    out.println("<h1>Sum"+sum+"</h1>");
  %>

  <%!
    static {
      System.out.println("Loading Servlet");
    }

    private int globalVar = 0;

    public void kong() {
      System.out.println("进入了方法kong!");
    }
  %>

  </body>
</html>
