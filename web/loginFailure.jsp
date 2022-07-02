<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-06-01
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int errorInfo = (int) session.getAttribute("loginError");
    if (errorInfo == 1) {
        out.println("用户名不存在");
        out.println("<a href=\"/Homework_72/login.jsp\">跳转到登录页面</a>");
    }
    else if (errorInfo == 2) {
        out.println("密码错误");
        out.println("<a href=\"/Homework_72/login.jsp\">跳转到登录页面</a>");
    }
    else if (errorInfo == 3) {
        out.println("验证码错误");
        out.println("<a href=\"/Homework_72/login.jsp\">跳转到登录页面</a>");
    }
    else if (errorInfo == -1) {
        out.println("数据库错误");
        out.println("<a href=\"/Homework_72/login.jsp\">跳转到登录页面</a>");
    }
%>
</body>
</html>
