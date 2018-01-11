package com.nys.web;

import com.nys.bean.User;
import com.nys.service.UserService;
import com.nys.service.exception.LoginException;
import com.nys.service.exception.RegisterException;
import com.nys.util.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = UserService.login(username, password);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
            return "f:/login.jsp";
        }
        request.getSession().setAttribute("user",user);
        if (!username.equals("root")){
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }
        return "r:/index.jsp";

    }
    public String register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username,password,1);
        try {
            UserService.register(user);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
            return "f:/register.jsp";
        }
        return "r:/login.jsp";
    }
    public String quit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "r:/login.jsp";
    }
}
