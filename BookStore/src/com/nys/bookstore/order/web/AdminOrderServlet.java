package com.nys.bookstore.order.web;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.order.domain.Order;
import com.nys.bookstore.order.domain.OrderItem;
import com.nys.bookstore.order.service.OrderService;
import com.nys.bookstore.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "AdminOrderServlet",urlPatterns = "/adminOrder")
public class AdminOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();
    public String loadAll(HttpServletRequest request, HttpServletResponse response){
        Map<Order, Map<OrderItem, Book>> all = orderService.findAll();
        request.setAttribute("orders",all);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    public String loadByState(HttpServletRequest request, HttpServletResponse response){
        String state = request.getParameter("state");
        Map<Order, Map<OrderItem, Book>> byState = orderService.findByState(Integer.parseInt(state));
        request.setAttribute("orders",byState);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    public String send(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");
        orderService.send(oid);
        return "f:/adminOrder?method=loadAll";
    }
}
