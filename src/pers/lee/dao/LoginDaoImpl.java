package pers.lee.dao;

import pers.lee.entity.Login;
import pers.lee.utils.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginDaoImpl {
    public static int isValid(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        String sql = "SELECT * FROM user WHERE uName = '" + username + "'";
        List<Map<String, Object>> userList = null;
        try {
            userList = DBUtil.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userList == null || userList.size() == 0) {
            return 1;
        }
        else {
            String pwd = (String) userList.get(0).get("uPw");
            if (pwd.equals(password)) {
                return 0;
            }
            return 2;
        }
    }
}
