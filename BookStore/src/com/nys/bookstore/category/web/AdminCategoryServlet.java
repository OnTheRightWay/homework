package com.nys.bookstore.category.web;

import com.nys.bookstore.category.dao.CategoryDao;
import com.nys.bookstore.category.domain.Category;
import com.nys.bookstore.category.service.CategoryService;
import com.nys.bookstore.category.service.exception.CategoryException;
import com.nys.bookstore.utils.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/adminCategory")
public class AdminCategoryServlet extends BaseServlet{
    CategoryService categoryService = new CategoryService();
    public String adminQueryAll(HttpServletRequest request, HttpServletResponse response){
        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.queryAll();
        request.setAttribute("categorys",list);
        return "f:/adminjsps/admin/category/list.jsp";
    }
    public String adminAdd(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> map = request.getParameterMap();
        Category category = new Category();
        try {
            BeanUtils.populate(category,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        category.setCid(UUID.randomUUID().toString());
        try {
            categoryService.add(category);
        } catch (CategoryException e) {
            request.setAttribute("add",e.getMessage());
            return "f:/adminjsps/admin/category/add.jsp";
        }
        return "f:/adminCategory?method=adminQueryAll";
    }
    public String adminDelete(HttpServletRequest request, HttpServletResponse response){
        String cname = request.getParameter("cname");
        try {
            categoryService.deleteByCid(cname);
        } catch (CategoryException e) {
            System.out.println(e.getMessage());
            request.setAttribute("deleteMsg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        return "f:/adminCategory?method=adminQueryAll";
    }
    public String edit(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> map = request.getParameterMap();
        Category category = new Category();
        try {
            BeanUtils.populate(category,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            categoryService.edit(category.getCid(),category);
        } catch (CategoryException e) {
            System.out.println(e.getMessage());
            request.setAttribute("editMsg",e.getMessage());
            return "f:/adminjsps/admin/category/mod.jsp";
        }
        return "f:/adminCategory?method=adminQueryAll";
    }
}
