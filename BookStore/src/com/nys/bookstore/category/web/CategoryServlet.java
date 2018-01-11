package com.nys.bookstore.category.web;

import com.nys.bookstore.cart.service.CartService;
import com.nys.bookstore.category.dao.CategoryDao;
import com.nys.bookstore.category.domain.Category;
import com.nys.bookstore.category.service.CategoryService;
import com.nys.bookstore.utils.BaseServlet;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    CategoryService categoryService = new CategoryService();
    public String queryAll(HttpServletRequest request,HttpServletResponse response){
        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.queryAll();
        request.setAttribute("category",list);
        return "f:/jsps/left.jsp";
    }

}
