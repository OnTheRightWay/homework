package com.nys.bookstore.user.web;

import com.nys.bookstore.cart.domain.Cart;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.user.service.UserService;
import com.nys.bookstore.user.service.exception.LoginException;
import com.nys.bookstore.user.service.exception.RegisterException;
import com.nys.bookstore.user.service.exception.UserException;
import com.nys.bookstore.utils.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    UserService userService = new UserService();
    public String register(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);
        try {
            userService.register(user);
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
            request.setAttribute("register",e.getMessage());
            request.setAttribute("user",user);
            return "f:/jsps/user/regist.jsp";
        }
        request.setAttribute("user",user);
        request.getSession().setAttribute("user",user);
        return "f:/jsps/msg.jsp";
    }
    public String active(HttpServletRequest request,HttpServletResponse response){
        String code = request.getParameter("code");
        User user=null;
        try {
            user = userService.active(code);
        } catch (UserException e) {
            System.out.println(e.getMessage());
            request.setAttribute("active",e.getMessage());
        }
        request.setAttribute("user",user);
        return "f:/jsps/msg.jsp";
    }
    public String login(HttpServletRequest request,HttpServletResponse response){

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            user= userService.login(user);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
            request.setAttribute("login",e.getMessage());
            request.setAttribute("user",user);
            return "f:/jsps/user/login.jsp";
        }
        request.getSession().setAttribute("user",user);
        Cart cart = new Cart(user.getUid());
        request.getSession().setAttribute("cart",cart);
        return "r:/index.jsp";
    }
    public String quit(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "r:/jsps/user/login.jsp";
    }
}
