package com.nys.web;

import com.nys.bean.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueryServlet",urlPatterns = "/query")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)getServletContext().getAttribute("user");
//        response.getWriter().write(user);
        JSONObject jsonObject=JSONObject.fromObject(user);
        response.getWriter().write(jsonObject.toString());
    }
}
