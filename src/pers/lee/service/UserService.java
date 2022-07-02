package pers.lee.service;

import pers.lee.dao.UserDao;

import java.util.Map;

public class UserService {
    public static Map<String, Object> getuInfoByName(String uName) {
        return UserDao.getuInfoByName(uName);
    }
}
