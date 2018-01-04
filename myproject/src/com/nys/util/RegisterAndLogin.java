package com.nys.util;

import com.nys.bean.User;
import com.nys.dao.UserDao;

public class RegisterAndLogin {
    public static boolean register(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if (username==null){
            return false;
        }
        if(password==null){
            return false;
        }
        if(!username.matches("[0-9]+@qq.com")){
            return false;

        }
        if (UserDao.queryPassword(username)!=null){
            return false;

        }
        UserDao.insert(user);
        return true;
    }
    public static boolean login(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if (username==null){
            return false;
        }
        if (password==null){
            return false;
        }
        String p = UserDao.queryPassword(username);
        if (p==null){
            return false;
        }
        if (p.equals(password)){
            return true;
        }
        return false;
    }
}
