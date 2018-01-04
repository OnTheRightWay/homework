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
    public Book queryByBname(String bname){
        Book book = null;
        try {
             book = queryUtil.query(
                    JDBCUtil.getConnection(),
                    "select * from Book where bname=?",
                    new BeanHandler<Book>(Book.class),
                    bname
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
                    "select * from Book",
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
                    "insert into Book values(null,?,?,?);",
                    book.getBname(),book.getPrice(),book.getAuthor()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
