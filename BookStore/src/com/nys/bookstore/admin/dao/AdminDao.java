package com.nys.bookstore.admin.dao;

import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class AdminDao {
    private QueryRunner queryRunner = QueryUtil.getQuery();
    public int queryLevelByUid(String uid){
        int level = 1;
        try {
            level = queryRunner.query(
                    "select level from level where uid=?",
                    new ScalarHandler<Integer>(),
                    uid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level;
    }
    public int queryLevelByUsername(String username){
        int level = 1;
        try {
            level = queryRunner.query(
                    "select level from level where uid=(select uid from user where username=?)",
                    new ScalarHandler<Integer>(),
                    username
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level;
    }
}
