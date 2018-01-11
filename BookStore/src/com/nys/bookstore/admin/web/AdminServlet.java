package com.nys.bookstore.admin.web;

import com.nys.bookstore.admin.service.AdminService;
import com.nys.bookstore.cart.domain.Cart;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.user.service.exception.LoginException;
import com.nys.bookstore.utils.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "AdminServlet",urlPatterns = "/admin")
public class AdminServlet extends BaseServlet {
    AdminService adminService = new AdminService();
    public String adminLogin(HttpServletRequest request, HttpServletResponse response){
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
            user = adminService.adminLogin(user);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
            request.setAttribute("login",e.getMessage());
            request.setAttribute("user",user);
            return "f:/adminjsps/login.jsp";
        }
        request.getSession().setAttribute("user",user);
        Cart cart = new Cart(user.getUid());
        request.getSession().setAttribute("cart",cart);
        return "r:/adminjsps/admin/main.jsp";
    }
}
