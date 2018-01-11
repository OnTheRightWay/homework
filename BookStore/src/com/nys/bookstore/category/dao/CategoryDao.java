package com.nys.bookstore.category.dao;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.category.domain.Category;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    QueryRunner queryRunner = QueryUtil.getQuery();
    public List<Category> queryAll(){
        List<Category> list = new ArrayList<>();
        try {
            list = queryRunner.query(
                    "select * from category",
                    new BeanListHandler<Category>(Category.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void insert(Category category){
        try {
            queryRunner.update(
                    "insert into category values(?,?)",
                    category.getCid(),category.getCname()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteByCname(String cname){
        try {
            queryRunner.update(
                    "delete from category where cname=?",
                    cname
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> queryBookByCname(String cname){
        List<Book> list= null;
        try {
            list = queryRunner.query(
                    "select * from book where cid=(select cid from category where cname=?)",
                     new BeanListHandler<Book>(Book.class),
                    cname
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Category queryByCname(String cname){
        Category category = null;
        try {
            category = queryRunner.query(
                    "select * from category where cname=?",
                    new ScalarHandler<Category>(),
                    cname
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    public void updateByCid(String cid,Category category){
        String id = category.getCid();
        String cname = category.getCname();
        try {
            queryRunner.update(
                    "update category set cid=?,cname=? where cid=?",
                    id,cname,cid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
