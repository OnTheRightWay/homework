package com.nys.web;

import com.nys.bean.Book;
import com.nys.dao.BookDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowBookServlet",urlPatterns = "/show")
public class ShowBookServlet extends HttpServlet {
    BookDao bookDao = new BookDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String bkid = request.getParameter("bkid");
        if (bkid==null) {
            List<Book> books = bookDao.queryAll();
            JSONArray jsonArray = JSONArray.fromObject(books);
            writer.write(jsonArray.toString());
            return;
        }
        Book book = bookDao.queryByBkid(Integer.parseInt(bkid));
        JSONObject jsonObject = JSONObject.fromObject(book);
        writer.write(jsonObject.toString());
//        response.sendRedirect(request.getContextPath()+"/show.jsp");
    }
}
