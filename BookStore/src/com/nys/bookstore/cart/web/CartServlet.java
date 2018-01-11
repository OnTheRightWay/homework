package com.nys.bookstore.cart.web;

import com.nys.bookstore.book.dao.BookDao;
import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.cart.domain.Cart;
import com.nys.bookstore.cart.service.CartService;
import com.nys.bookstore.cart.service.exception.InputStringException;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends BaseServlet{
    CartService cartService = new CartService();
    BookDao bookDao = new BookDao();
    public String add(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        String count = request.getParameter("count");
//        User user = (User)request.getSession().getAttribute("user");
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Book book = bookDao.queryByBid(bid);
        Cart add = null;
        try {
            add = cartService.add(book,count, cart);
        } catch (InputStringException e) {
            request.setAttribute("add",e.getMessage());
            request.setAttribute("book",book);
            request.setAttribute("input",count);
            return "f:/jsps/book/desc.jsp";
        }
//        request.getSession().setAttribute(user.getUid(),add);
        request.getSession().setAttribute("cart",add);
        return "f:/jsps/cart/list.jsp";
    }
    public String clear(HttpServletRequest request, HttpServletResponse response){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.getCartItem().clear();
        cart.setPrice(0);
        request.getSession().setAttribute("cart",cart);
        return "f:/jsps/cart/list.jsp";
    }
    public String delete(HttpServletRequest request,HttpServletResponse response){
        String bid = request.getParameter("bid");
        Book book = bookDao.queryByBid(bid);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Cart delete = cartService.delete(book, cart);
        request.getSession().setAttribute("cart",delete);
        return "f:/jsps/cart/list.jsp";
    }
}
