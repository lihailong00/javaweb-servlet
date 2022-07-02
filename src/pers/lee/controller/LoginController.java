package pers.lee.controller;

import pers.lee.entity.Login;
import pers.lee.entity.User;
import pers.lee.service.LoginService;
import pers.lee.service.UserService;
import pers.lee.utils.Constant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *  1. 登录失败的信息放在session中，命名为：loginError
         *  2. 登录成功，将个人信息全部保存到名为Constant.USER_SESSION的session中，类型为User
         * */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        Login login = new Login(username, password);
        if (code.equals("7364")) {
            int loginInfo = -1;
            loginInfo = LoginService.isValid(login);
            if (loginInfo == 0) {  // 登录成功，将个人信息全部保存到名为Constant.USER_SESSION的session中
                Map<String, Object> uInfo = UserService.getuInfoByName(username);
                int uId = (int)(long) uInfo.get("uId");
                String uName = (String) uInfo.get("uName");
                String uPw = (String) uInfo.get("uPw");
                String uSchool = (String) uInfo.get("uSchool");
                String uDepartment = (String) uInfo.get("uDepartment");
                User user = new User(uId, uName, uPw, uSchool, uDepartment);
                request.getSession().setAttribute(Constant.USER_SESSION, user);
                response.sendRedirect("/Homework_72/jsp/main.jsp");
            }
            else {
                request.getSession().setAttribute("loginError", loginInfo);
                response.sendRedirect("/Homework_72/loginFailure.jsp");
            }
        }
        else {
            request.getSession().setAttribute("loginError", 3);
            response.sendRedirect("/Homework_72/loginFailure.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
