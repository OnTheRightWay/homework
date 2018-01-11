package com.nys.bookstore.admin.dao;

import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class LevelDao {
    QueryRunner queryRunner = QueryUtil.getQuery();
    public void updateLevel(String uid,int level){
        try {
            queryRunner.update(
                    "update level set level=? where uid=?",
                    level,uid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(String uid,int level){
        try {
            queryRunner.update(
                    "insert into level values(?,?)",
                    uid,level
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
