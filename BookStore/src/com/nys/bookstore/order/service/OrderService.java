package com.nys.bookstore.order.service;

import com.nys.bookstore.book.dao.BookDao;
import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.order.dao.OrderDao;
import com.nys.bookstore.order.dao.OrderItemDao;
import com.nys.bookstore.order.domain.Order;
import com.nys.bookstore.order.domain.OrderItem;
import com.nys.bookstore.order.service.exception.OrderException;
import com.nys.bookstore.order.service.exception.OrderStateException;

import java.sql.Connection;
import java.util.*;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    public void addOrder(Connection conn,Order order) {
        orderDao.insert(conn,order);
    }
    public void addOrderItem(Connection conn,OrderItem orderItem){
        orderItemDao.insert(conn,orderItem);
    }

    public Map<Order,Map<OrderItem,Book>> findByUid(String uid){
        Map<Order,Map<OrderItem,Book>> map = new Hashtable<>();
        List<Order> orders = orderDao.queryByUid(uid);
        for (Order order:orders) {
            List<OrderItem> orderItems = orderItemDao.queryByOid(order.getOid());
            Map<OrderItem,Book> orderMap = getOrderMap(orderItems);
            map.put(order,orderMap);
        }
        return map;
    }
    public Map<OrderItem,Book> getOrderMap(List<OrderItem> orderItems){
        Map<OrderItem,Book> map = new Hashtable<>();
        for (OrderItem orderItem:orderItems) {
            Book book = new BookDao().queryByBid(orderItem.getBid());
            map.put(orderItem,book);
        }
        return map;
    }
    public Order findOrderByOid(String oid){
        Order order = orderDao.queryByOid(oid);

        return order;
    }
    public List<OrderItem> findOrderItemByOid(String oid){
        List<OrderItem> orderItems = orderItemDao.queryByOid(oid);
        return orderItems;
    }
    public void confirm(String oid) throws OrderException{
        int state = orderDao.getStateByOid(oid);
        if (state!=2){
            throw new OrderStateException();
        }else {
            orderDao.updateState(oid,3);
        }
    }
    public void pay(String oid,String address){
        int state = orderDao.getStateByOid(oid);
        if (state==0){
            orderDao.updateState(oid,1);
            orderDao.updateAddress(oid,address);
        }
    }
    public Map<Order,Map<OrderItem,Book>> findAll(){
        List<Order> orders = orderDao.queryAll();
        Map<Order,Map<OrderItem,Book>> map = new HashMap<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemDao.queryByOid(order.getOid());
            Map<OrderItem, Book> orderMap = getOrderMap(orderItems);
            map.put(order,orderMap);
        }
        return map;
    }
    public Map<Order,Map<OrderItem,Book>> findByState(int state){
        List<Order> orders = orderDao.queryByState(state);
        Map<Order,Map<OrderItem,Book>> map = new HashMap<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemDao.queryByOid(order.getOid());
            Map<OrderItem, Book> orderMap = getOrderMap(orderItems);
            map.put(order,orderMap);
        }
        return map;
    }
    public void send(String oid){
        orderDao.updateState(oid,2);
    }
}
