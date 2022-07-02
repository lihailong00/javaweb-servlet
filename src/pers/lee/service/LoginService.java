package pers.lee.service;

import pers.lee.dao.LoginDaoImpl;
import pers.lee.entity.Login;

public class LoginService {
    public static int isValid(Login login) {
        return LoginDaoImpl.isValid(login);
    }
}
