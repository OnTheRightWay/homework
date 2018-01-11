package com.nys.bookstore.user.service;

import com.nys.bookstore.admin.dao.LevelDao;
import com.nys.bookstore.user.dao.UserDao;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.user.service.exception.*;
import com.nys.bookstore.utils.EmailUtil;

import java.security.GeneralSecurityException;
import java.util.Random;
import java.util.UUID;

public class UserService {
    private UserDao userDao=new UserDao();
    public void register(User user)throws RegisterException{
        if (user.getUsername()==null||"".equals(user.getUsername())){
            throw new UsernameIsNullException();
        }
        if (user.getPassword()==null||"".equals(user.getPassword())){
            throw new PasswordIsNullException();
        }
        if (user.getEmail()==null||"".equals(user.getEmail())){
            throw new EmailIsNullException();
        }
        if (!user.getEmail().matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")){
            throw new EmailIsNotFormatException();
        }
        if (userDao.queryByUsername(user.getUsername())!=null){
            throw new UsernameIsExistException();
        }
        if (userDao.queryByEmail(user.getEmail())!=null){
            throw new EmailIsExistException();
        }
        UUID uuid = UUID.randomUUID();
        String code = UUID.randomUUID().toString()+uuid.toString();
        user.setUid(uuid.toString());
        user.setCode(code);
        userDao.insert(user);
        new LevelDao().insert(uuid.toString(),1);
        try {
            EmailUtil.sendEmail(user);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
//        EmailUtil.sendHTMLEmail(user);
    }
    public User active(String code) throws UserException {
        User user = null;
        user=userDao.queryByCode(code);
        if (user==null){
            throw new CodeErrorException();
        }
        if (!"1".equals(user.getState())){
            user.setCode("1");
            userDao.setStateByCode(code);
        }else {
            throw new StateIsTrueException();
        }
        return user;
    }
    public User login(User userForm)throws LoginException{
        String username = userForm.getUsername();
        String password = userForm.getPassword();
        User user = userDao.queryByUsername(username);
        if (user==null){
            throw new UserIsNotExistException();
        }
        if (!user.getPassword().equals(password)){
            throw new PasswordErrorException();
        }
        if (!user.getState().equals("1")){
            throw new StateNotTrueException();
        }
        return user;
    }
}
