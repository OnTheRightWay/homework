package com.nys.service;

import com.nys.bean.User;
import com.nys.dao.UserDao;
import com.nys.service.exception.*;

public class UserService {
    private static UserDao userDao = new UserDao();
    public static boolean register(User user)throws RegisterException{
        String username = user.getUsername();
        String password = user.getPassword();
        if (username==null||password==null){
            throw new UsernameNotFormatException();
        }
        if(!username.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")){
            throw new UsernameNotFormatException();
        }
        if (userDao.queryPassword(username)!=null){
            throw new UserIsExistException();
        }
        userDao.insert(user);
        return true;
    }
    public static User login(String username,String password) throws LoginException {
        if (username==null||password==null){
            throw new InputIsNullException();
        }
        String p = userDao.queryPassword(username);
        if (p==null){
            throw new UserNotExistException();
        }
        if (p.equals(password)){
            return new UserDao().queryByUsername(username);
        }
        throw new PasswordErrorException();
    }
}
