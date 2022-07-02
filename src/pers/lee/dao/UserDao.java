package pers.lee.dao;

import pers.lee.utils.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public static Map<String, Object> getuInfoByName(String uName) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("uName", uName);
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            userList = DBUtil.query("user", whereMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList.get(0);
    }

}
