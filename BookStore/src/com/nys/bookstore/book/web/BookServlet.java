package com.nys.bookstore.book.web;

import com.nys.bookstore.book.dao.BookDao;
import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.utils.BaseServlet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet {
    private BookDao bookDao = new BookDao();
    public String findAll(HttpServletRequest request,HttpServletResponse response){
        List<Book> list = bookDao.queryAll();
        JSONArray jsonArray = JSONArray.fromObject(list);
        try {
            response.getWriter().write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        request.setAttribute("books",list);
//        return "f:/jsps/book/list.jsp";
        return "";
    }
    public String findByCname(HttpServletRequest request,HttpServletResponse response){
        String cname = request.getParameter("cname");
        List<Book> books = bookDao.queryByCname(cname);
        JSONArray jsonArray = JSONArray.fromObject(books);
        try {
            response.getWriter().write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        request.setAttribute("books",books);
//        return "f:/jsps/book/list.jsp";
        return "";
    }
    public String showBook(HttpServletRequest request,HttpServletResponse response){
        String bid = request.getParameter("bid");
        Book book = bookDao.queryByBid(bid);
        request.setAttribute("book",book);
        return "f:/jsps/book/desc.jsp";
    }

}
