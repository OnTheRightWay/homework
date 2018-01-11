package com.nys.bookstore.book.web;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.book.service.BookService;
import com.nys.bookstore.category.dao.CategoryDao;
import com.nys.bookstore.category.domain.Category;
import com.nys.bookstore.category.service.CategoryService;
import com.nys.bookstore.utils.BaseServlet;
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

@WebServlet(name = "AdminBookServlet",urlPatterns = "/adminBook")
public class AdminBookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    public String findAll(HttpServletRequest request, HttpServletResponse response){
        List<Book> books = bookService.findAll();
        request.setAttribute("books",books);
        return "f:/adminjsps/admin/book/list.jsp";
    }
    public String load(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book",book);
        List<Category> categories = new CategoryDao().queryAll();
        request.setAttribute("categories",categories);
        return "f:/adminjsps/admin/book/desc.jsp";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        bookService.delete(bid);
        return "f:/adminBook?method=findAll";
    }
    public String edit(HttpServletRequest request, HttpServletResponse response){
        String price = request.getParameter("price");
        if (!price.matches("[0-9]+.*[0-9]*")){
            request.setAttribute("price","输入错误");
            return "f:/adminjsps/admin/book/add.jsp";
        }
        Map<String, String[]> map = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        bookService.edit(book);
//        System.out.println(book);
        return "f:/adminBook?method=findAll";
    }
}
