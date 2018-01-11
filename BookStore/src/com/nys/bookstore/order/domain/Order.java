package com.nys.bookstore.order.domain;

public class Order {
    private String oid;
    private String ordertime;
    private double price;
    private int state;
    private String uid;
    private String address;

    public Order() {
    }

    public Order(String oid, String ordertime, double price, int state, String uid, String address) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.price = price;
        this.state = state;
        this.uid = uid;
        this.address = address;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return oid.equals(order.oid);
    }

    @Override
    public int hashCode() {
        return oid.hashCode();
    }
}
