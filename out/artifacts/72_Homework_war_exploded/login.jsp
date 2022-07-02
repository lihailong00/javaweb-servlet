<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-06-01
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .demo {
            width: 50%;
        }
        img {
            vertical-align: bottom;
        }
    </style>
</head>

<body>
<form action="/Homework_72/LoginController">
    <table>
        <tr>
            <td>
                <label for="username">用户：</label>
            </td>
            <td>
                <input type="text" id="username" name="username" required>
            </td>
        </tr>

        <tr>
            <td>
                <label for="password">密码：</label>
            </td>
            <td>
                <input type="password" id="password" name="password" required>
            </td>
        </tr>

        <tr>
            <td>
                <label for="code">验证码：</label>
            </td>
            <td>
                <input type="number" class="demo" id="code" name="code" autocomplete="off" required>
                <img src="<c:url value="/code.png"/>" width="60px" height="22px"/>
            </td>
        </tr>

        <tr>
            <td>
                <label for="schools">所在学院：</label>
            </td>
            <td>
                <select id="schools" name="schools" onChange="set_department(this, this.form.department);" required="required">
                    <option value="0">选择学院</option>
                    <option value="计算机学院">计算机学院</option>
                    <option value="电光学院">电光学院</option>
                </select>

            </td>
        </tr>

        <tr>
            <td>
                <label for="departments">所在系：</label>
            </td>
            <td>
                <select name="department" id="departments" required="required">
                    <option value="0">选择系</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="登录">
            </td>
            <td>
                <a href="">帮助</a>
                <a href="">忘密</a>
            </td>
        </tr>

    </table>
</form>

<script type="text/javascript">
    departments = new Object();
    departments['计算机学院'] = new Array('软件工程', '计算机科学与技术', '人工智能');
    departments['电光学院'] = new Array('电子信息工程', '通信工程', '电子科学与技术', '光电信息科学与技术', '微电子科学与工程', '探测制导与控制技术');

    function set_department(schools, department) {
        var pv, cv;
        var i, ii;

        pv = schools.value;
        cv = department.value;

        department.length = 1;

        if (pv === '0') return;
        if (typeof (departments[pv]) == 'undefined') return;

        for (i = 0; i < departments[pv].length; i++) {
            ii = i + 1;
            department.options[ii] = new Option();
            department.options[ii].text = departments[pv][i];
            department.options[ii].value = departments[pv][i];
        }
    }
</script>
</body>

</html>


