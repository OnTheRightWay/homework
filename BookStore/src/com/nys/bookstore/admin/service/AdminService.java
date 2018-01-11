package com.nys.bookstore.admin.service;

import com.nys.bookstore.admin.dao.AdminDao;
import com.nys.bookstore.user.dao.UserDao;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.user.service.exception.*;

public class AdminService {
    private AdminDao adminDao = new AdminDao();
    private UserDao userDao = new UserDao();
    public User adminLogin(User userForm) throws LoginException {
        String username = userForm.getUsername();
        String password = userForm.getPassword();
        User user = userDao.queryByUsername(username);
        int level = adminDao.queryLevelByUsername(username);
        if (user==null){
            throw new UserIsNotExistException();
        }
        if (!user.getPassword().equals(password)){
            throw new PasswordErrorException();
        }
        if (!user.getState().equals("1")){
            throw new StateNotTrueException();
        }
        if (level!=0){
            throw new LevelErrorException();
        }
        return user;
    }
}
