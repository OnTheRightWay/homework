package com.nys.web;

import com.nys.bean.Book;
import com.nys.bean.User;
import com.nys.dao.BookDao;
import com.nys.util.RegisterAndLogin;
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

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
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
        boolean register = RegisterAndLogin.register(user);
        if (!register){
            response.sendRedirect("http://localhost:8080/Day29/register.html");
            return;
        }
        response.sendRedirect("http://localhost:8080/Day29/login.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String bname = request.getParameter("bname");
        BookDao bookDao = new BookDao();
        Book book = bookDao.queryByBname(bname);
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = JSONObject.fromObject(book);
        writer.write(jsonObject.toString());
//        getServletContext().setAttribute("url","http://localhost:8080/Day29/register?bname="+bname);
//        response.sendRedirect("http://localhost:8080/Day29/show.jsp");

    }
}
