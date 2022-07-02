<%@ page import="pers.lee.utils.Variable" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-06-02
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Variable.ack = false;
    int delCount = 0;
    delCount = (int) session.getAttribute("orderFailure");
    if (delCount != 0) {
        out.println(delCount + "条订单消息没有达到时间，未成功删除！");
    }
    else {
        out.println("成功删除所选订单！");
    }
%>
<a href="${pageContext.request.contextPath}/jsp/allOrder.jsp">返回订单首页</a>
</body>
</html>
