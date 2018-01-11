package com.nys.bookstore.order.dao;

import com.nys.bookstore.order.domain.OrderItem;
import com.nys.bookstore.utils.QueryUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    QueryRunner queryRunner = QueryUtil.getQuery();
    public void insert(OrderItem orderItem){
        try {
            queryRunner.update(
                    "insert into orderitem values(?,?,?,?,?)",
                    orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),
                    orderItem.getOid(),orderItem.getBid()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(Connection conn,OrderItem orderItem){
        try {
            queryRunner.update(
                    conn,
                    "insert into orderitem values(?,?,?,?,?)",
                    orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),
                    orderItem.getOid(),orderItem.getBid()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<OrderItem> queryByOid(String oid){
        List<OrderItem> orderItems = null;
        try {
            orderItems = queryRunner.query(
                    "select * from orderitem where oid=?",
                    new BeanListHandler<OrderItem>(OrderItem.class),
                    oid
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
