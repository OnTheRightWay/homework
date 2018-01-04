package com.nys.web;

import com.nys.bean.Book;
import com.nys.dao.BookDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "HomepageServlet",urlPatterns = "/home")
public class HomepageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookDao bookDao = new BookDao();
        Map<String, String[]> map = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String bname = book.getBname();
        double price = book.getPrice();
        String cover = book.getAuthor();
        if (bname==null||cover==null){
            response.sendRedirect("http://localhost:8080/Day29/index.jsp");
            System.out.println("book插入失败");
            return;
        }
        bookDao.insert(book);
        System.out.println("book插入成功");
        response.sendRedirect("http://localhost:8080/Day29/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =(String) request.getSession().getAttribute("username");
        request.getSession().removeAttribute("username");
        getServletContext().setAttribute("username",username);
        response.sendRedirect("http://localhost:8080/Day29/login.jsp");
    }
}
