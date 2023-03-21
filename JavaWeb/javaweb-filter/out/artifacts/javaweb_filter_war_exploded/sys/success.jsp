<%--
  Created by IntelliJ IDEA.
  User: xykong
  Date: 2022/4/19
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Object userSession = request.getSession().getAttribute("USER_SESSION");
    if (userSession == null) {
        response.sendRedirect("/login.jsp");
    }
%>

<h1>主页</h1>

<p><a href="/servlet/logout">注销</a></p>


</body>
</html>
