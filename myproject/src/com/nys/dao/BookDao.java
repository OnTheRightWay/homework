package com.nys.dao;

import com.nys.bean.Book;
import com.nys.util.JDBCUtil;
import com.nys.util.QueryUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {
    QueryUtil queryUtil = new QueryUtil();
    public List<Book> queryByBname(String bkname){
        List<Book> books = null;
        try {
              books = queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select * from book where bkname=?",
                    new BeanListHandler<Book>(Book.class),
                    bkname
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Book queryByBkid(int bkid){
        Book book = null;
        try {
            book = queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select * from book where bkid=?",
                    new BeanHandler<Book>(Book.class),
                    bkid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public List<Book> queryAll(){
        List<Book> books = null;
        try {
            books = queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select * from book",
                    new BeanListHandler<Book>(Book.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public void insert(Book book){
        try {
            queryUtil.update(
                    JDBCUtil.getConnection(),
                    "insert into book values(?,?,?,?,?,?,?);",
                    book.getBkid(),book.getBkname(),book.getAuthor(),
                    book.getPrice(),book.getCover(),book.getDetails(),book.getType()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteByBkid(String bkid){
        try {
            queryUtil.update(
                    JDBCUtil.getConnection(),
                    "delete from book where bkid=?",
                    bkid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
