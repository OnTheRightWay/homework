package com.nys.util;

import com.nys.bean.User;
import com.nys.dao.Dao;

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
        if (Dao.queryPassword(username)!=null){
            return false;

        }
        Dao.insert(user);
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
        String p = Dao.queryPassword(username);
        if (p==null){
            return false;
        }
        if (p.equals(password)){
            return true;
        }
        return false;
    }
}
