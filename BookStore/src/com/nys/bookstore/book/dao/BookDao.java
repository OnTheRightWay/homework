package com.nys.bookstore.book.dao;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    QueryRunner queryRunner = QueryUtil.getQuery();
    public List<Book> queryAll(){
        List<Book> list = null;
        try {
            list = queryRunner.query(
                    "select * from book where del='0'",
                    new BeanListHandler<Book>(Book.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Book> queryByCname(String cname){
        List<Book> list = null;
        try {
            list = queryRunner.query(
                    "select * from book where cid=(" +
                            "select cid from category where cname=?) and del='0'",
                    new BeanListHandler<Book>(Book.class),
                    cname
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Book queryByBid(String bid){
        Book book = null;
        try {
            book = queryRunner.query(
                    "select * from book where bid=? and del='0'",
                    new BeanHandler<Book>(Book.class),
                    bid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public void insert(Book book){
        try {
            queryRunner.update(
                    "insert into book values(?,?,?,?,?,?,'0')",
                    book.getBid(),book.getBname(),book.getPrice(),
                    book.getAuthor(),book.getImage(),book.getCid()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(Connection conn,Book book){
        try {
            queryRunner.update(
                    conn,
                    "insert into book values(?,?,?,?,?,?,'0')",
                    book.getBid(),book.getBname(),book.getPrice(),
                    book.getAuthor(),book.getImage(),book.getCid()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDel(String bid){
        try {
            queryRunner.update(
                    "update book set del='1' where bid=?",
                    bid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection conn, String bid) {
        try {
            queryRunner.update(
                    conn,
                    "delete from book where bid=? and del='0'",
                    bid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void edit(String bid,Book book){
        try {
            queryRunner.update(
                    "update book set bname=?,price=?,author=?,image=?," +
                            "cid=?,del='0' where bid=?",
                    book.getBname(),book.getPrice(),book.getAuthor(),
                    book.getImage(),book.getCid(),book.getBid()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
