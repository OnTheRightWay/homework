package com.nys.bookstore.admin.web;

import com.nys.bookstore.admin.dao.AdminDao;
import com.nys.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = {"/adminjsps/admin/main.jsp","/adminCategory"})
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect(request.getContextPath()+"/adminjsps/login.jsp");
            return;
        }
        int level = new AdminDao().queryLevelByUid(user.getUid());
        if (level==0){
            request.getSession().setAttribute("admin",user);
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect(request.getContextPath()+"/adminjsps/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
