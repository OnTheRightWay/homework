package com.nys.web;

import com.nys.bean.Book;
import com.nys.bean.User;
import com.nys.dao.BookDao;
import com.nys.util.RegisterAndLogin;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean login = RegisterAndLogin.login(user);
        if (!login){
            response.sendRedirect("http://localhost:8080/Day29/login.jsp");
            return;
        }
        getServletContext().setAttribute("user",user);
        request.getSession().setAttribute("username",user.getUsername());
        response.sendRedirect("http://localhost:8080/Day29/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryAll();
        PrintWriter writer = response.getWriter();
        if (books==null){
            writer.write("");
            return;
        }
        JSONArray jsonArray =JSONArray.fromObject(books);
        writer.write(jsonArray.toString());
    }
}
