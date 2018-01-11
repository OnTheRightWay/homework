package com.nys.web;

import com.nys.bean.Book;
import com.nys.dao.BookDao;
import com.nys.util.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet {
    BookDao bookDao = new BookDao();

//    public String insert(HttpServletRequest request,HttpServletResponse response){
//
//
//    }


}
