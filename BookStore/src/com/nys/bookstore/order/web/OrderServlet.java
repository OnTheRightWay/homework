package com.nys.bookstore.order.web;

import com.nys.bookstore.book.dao.BookDao;
import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.cart.domain.Cart;
import com.nys.bookstore.cart.web.CartServlet;
import com.nys.bookstore.order.domain.Order;
import com.nys.bookstore.order.domain.OrderItem;
import com.nys.bookstore.order.service.OrderService;
import com.nys.bookstore.order.service.exception.OrderException;
import com.nys.bookstore.user.domain.User;
import com.nys.bookstore.utils.BaseServlet;
import com.nys.bookstore.utils.JDBCUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderService();
    private CartServlet cartServlet = new CartServlet();
    public String add(HttpServletRequest request, HttpServletResponse response){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart.getCartItem()==null){
            return "f:/order?method=findByUid";
        }
        User user = (User) request.getSession().getAttribute("user");
        String address = null;
//        request.getParameter("address");
        String oid = UUID.randomUUID().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        double price = cart.getPrice();
        int state = 0;
        String uid = user.getUid();
        Order order = new Order(
                oid,
                date,
                price,
                state,
                uid,
                address
        );
        List<OrderItem> orderItems = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Map<Book, Integer> cartItem = cart.getCartItem();
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            orderService.addOrder(conn,order);

            for (Book book:cartItem.keySet()) {
                String iid = UUID.randomUUID().toString();
                int count = cartItem.get(book);
                double subtotal = count*book.getPrice();
                String bid = book.getBid();
                OrderItem orderItem =new OrderItem(iid,count,subtotal,oid,bid);
                orderItems.add(orderItem);
                books.add(book);
                orderService.addOrderItem(conn,orderItem);
            }
            cartServlet.clear(request,response);

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        request.setAttribute("order",order);
        request.setAttribute("orderItems",orderItems);
        request.setAttribute("books",books);
        return "f:/jsps/order/desc.jsp";
    }
    public String findByUid(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        Map<Order, Map<OrderItem,Book>> orders = orderService.findByUid(user.getUid());
        request.setAttribute("orders",orders);
        return "f:/jsps/order/list.jsp";
    }
    public String load(HttpServletRequest request,HttpServletResponse response){
        String oid = request.getParameter("oid");
        Order order= orderService.findOrderByOid(oid);
        List<OrderItem> orderItems = orderService.findOrderItemByOid(oid);
        request.setAttribute("order",order);
        request.setAttribute("orderItems",orderItems);
        List<Book> books = new ArrayList<>();
        for (OrderItem o :orderItems) {
            Book book = new BookDao().queryByBid(o.getBid());
            books.add(book);
        }
        request.setAttribute("books",books);
        return "f:/jsps/order/desc.jsp";
    }
    public String confirm(HttpServletRequest request,HttpServletResponse response){
        String oid = request.getParameter("oid");
        try {
            orderService.confirm(oid);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
            request.setAttribute("confirm",e.getMessage());
            return "f:/jsps/order/msg.jsp";
        }
        request.setAttribute("confirm","SUCCESS");
        return "f:/order?method=findByUid";
    }
    public String pay(HttpServletRequest request,HttpServletResponse response){
        String oid = request.getParameter("oid");
        String address = request.getParameter("address");
        orderService.pay(oid,address);
        return "f:/order?method=findByUid";
    }
}
