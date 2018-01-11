package com.nys.bookstore.cart.domain;

import com.nys.bookstore.book.domain.Book;

import java.util.List;
import java.util.Map;

public class Cart {
    private String uid;
    private Map<Book,Integer> cartItem;
    private double price=0;

    public Cart(String uid) {
        this.uid = uid;
    }

    public Cart() {
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<Book, Integer> getCartItem() {
        return cartItem;
    }

    public void setCartItem(Map<Book, Integer> cartItem) {
        this.cartItem = cartItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
