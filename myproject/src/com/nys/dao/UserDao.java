package com.nys.dao;

import com.nys.bean.User;
import com.nys.util.JDBCUtil;
import com.nys.util.QueryUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static QueryUtil queryUtil = new QueryUtil();
    public static void insert(User user){
        try {
            queryUtil.update(
                    JDBCUtil.getConnection(),
                    "insert into user values(?,?);",
                    user.getUsername(),user.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String queryPassword(String username){
        String password = null;
        try {
            password= queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select password from user where username=?",
                    new ScalarHandler<String>(),
                    username
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
    public static List<User> queryAll(){
        List<User> users = null;
        try {
            users = queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select * from user",
                    new BeanListHandler<User>(User.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
