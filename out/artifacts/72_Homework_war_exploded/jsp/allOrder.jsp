<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-06-01
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="pers.lee.entity.User" %>
<%@ page import="pers.lee.utils.Constant" %>
<%@ page import="pers.lee.utils.Variable" %>
<%@ page import="pers.lee.entity.Orders" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
            border: 1px solid black;
        }
        td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    if (!Variable.ack) {
        response.sendRedirect("/Homework_72/OrderController?option=getOrdersByuId");
    }
    Variable.ack = false;
    User user = (User) session.getAttribute(Constant.USER_SESSION);
    List<Orders> orders = (List<Orders>) session.getAttribute("orders");
%>
用户：<%=user.getuName()%>
<form action="${pageContext.request.contextPath}/OrderController">
    <table>
        <th>
        <td>序号</td>
        <td>订单ID</td>
        <td>订单时间</td>
        <td>订购菜肴</td>
        <td>订单总价</td>
        </th>
        <%
            if (orders != null) {
                int id = 0;
                for (Orders order : orders) {
                    id++;
        %>
        <tr>
            <td>
                <input type="checkbox" name="foodIds" value="<%=order.getoId()%><%="+"%><%=order.getoTime()%><%="+"%><%=order.getoItems()%><%="+"%><%=order.getTotalPrice()%><%="+"%>">
            </td>
            <td><%=id%></td>
            <td><%=order.getoId()%></td>
            <td><%=order.getoTime()%></td>
            <td><%=order.getoItems()%></td>
            <td><%=order.getTotalPrice()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <a href="#">浏览菜肴</a>
    <input type="submit" value="删除订单" name="option">
    <input type="submit" value="撤销订单" name="option">
</form>
</body>
</html>
