<%@ page import="pers.lee.utils.Variable" %>
<%@ page import="pers.lee.entity.Orders" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-06-02
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
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
    Variable.ack = false;
%>
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
            List<Orders> cancelledOrders = (List<Orders>) session.getAttribute("checkedOrders");
            if (cancelledOrders != null) {
                int cnt = 0;
                for (Orders order : cancelledOrders) {
                    cnt++;
        %>
        <tr>
            <td></td>
            <td><%=cnt%></td>
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
    <a href="${pageContext.request.contextPath}/jsp/allOrder.jsp">取消删除</a>
    <input type="submit" value="确认删除" name="option">
</form>
</body>
</html>
