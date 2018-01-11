package com.nys.bookstore.user.dao;

import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao{
    QueryRunner queryRunner = QueryUtil.getQuery();
    public void setStateByCode(String code){
        try {
            queryRunner.update(
                    "update user set state='1' where code=?",
                    code
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(User user){
        try {
            queryRunner.update(
                    "insert into user values(?,?,?,?,?,?)",
                    user.getUid(),user.getUsername(),user.getPassword(),
                    user.getEmail(),user.getCode(),user.getState()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User queryByUid(String uid){
        User user=null;
        try {
            user= queryRunner.query(
                    "select * from user where uid=?",
                    new BeanHandler<User>(User.class),
                    uid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User queryByCode(String code){
        User user=null;
        try {
            user= queryRunner.query(
                    "select * from user where code=?",
                    new BeanHandler<User>(User.class),
                    code
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User queryByUsername(String username){
        User user=null;
        try {
            user= queryRunner.query(
                    "select * from user where username=?",
                    new BeanHandler<User>(User.class),
                    username
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User queryByEmail(String email){
        User user=null;
        try {
            user= queryRunner.query(
                    "select * from user where email=?",
                    new BeanHandler<User>(User.class),
                    email
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}