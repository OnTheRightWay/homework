package com.nys.bookstore.book.service;

import com.nys.bookstore.book.dao.BookDao;
import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    public List<Book> findAll(){
        List<Book> books = bookDao.queryAll();
        return books;
    }
    public Book load(String bid){
        return bookDao.queryByBid(bid);
    }
    public void insert(Book book){
        bookDao.insert(book);
    }
    public void delete(String bid){
        bookDao.updateDel(bid);
    }
    public void edit(Book book){
        Book query = bookDao.queryByBid(book.getBid());
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            bookDao.delete(conn,book.getBid());
            bookDao.insert(conn,book);
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
