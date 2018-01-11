package com.nys.bookstore.order.dao;

import com.nys.bookstore.order.domain.Order;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    QueryRunner queryRunner = QueryUtil.getQuery();
    public void insert(Order order){
        try {
            queryRunner.update(
                    "insert into orders values(?,?,?,?,?,?)",
                    order.getOid(),order.getOrdertime(),order.getPrice(),
                    order.getState(),order.getUid(),order.getAddress()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(Connection conn,Order order){
        try {
            queryRunner.update(
                    conn,
                    "insert into orders values(?,?,?,?,?,?)",
                    order.getOid(),order.getOrdertime(),order.getPrice(),
                    order.getState(),order.getUid(),order.getAddress()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Order> queryByUid(String uid){
        List<Order> orders = null;
        try {
            orders = queryRunner.query(
                    "select * from orders where uid=?",
                    new BeanListHandler<Order>(Order.class),
                    uid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Order queryByOid(String oid){
        Order order = null;
        try {
            order = queryRunner.query(
                    "select * from orders where oid=?",
                    new BeanHandler<Order>(Order.class),
                    oid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    public int getStateByOid(String oid){
        int a = 0;
        try {
            a = queryRunner.query(
                    "select state from orders where oid=?",
                    new ScalarHandler<Integer>(),
                    oid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    public void updateState(String oid,int state){
        try {
            queryRunner.update(
                    "update orders set state=? where oid=?",
                    state,oid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Order> queryAll(){
        List<Order> list = null;
        try {
            list = queryRunner.query(
                    "select * from orders",
                    new BeanListHandler<Order>(Order.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Order> queryByState(int state){
        List<Order> list = null;
        try {
            list = queryRunner.query(
                    "select * from orders where state=?",
                    new BeanListHandler<Order>(Order.class),
                    state
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateAddress(String oid,String address) {
        try {
            queryRunner.update(
                    "update orders set address=? where oid=?",
                    address,oid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
