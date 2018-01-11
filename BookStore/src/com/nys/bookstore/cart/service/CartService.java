package com.nys.bookstore.cart.service;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.cart.domain.Cart;
import com.nys.bookstore.cart.service.exception.InputStringException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    public Cart add(Book book,String countString,Cart cart) throws InputStringException {
//        List<String> bids = cart.getBids();
        int count = 0;
        if (!countString.matches("[0-9]+")){
            throw new InputStringException();
        }
        else {
            count = Integer.parseInt(countString);
        }
        Map<Book, Integer> cartItem = cart.getCartItem();
        double price = cart.getPrice();
        if (cartItem==null){
            cartItem = new HashMap<>();
        }
        Integer c = cartItem.get(book);
        if (c==null||c==0){
            cartItem.put(book,count);
        }else {
            c=c+count;
            cartItem.put(book,c);
        }
        price=price+book.getPrice()*count;
        cart.setPrice(price);
        cart.setCartItem(cartItem);
        return cart;
    }
    public Cart delete(Book book,Cart cart){
        Map<Book, Integer> cartItem = cart.getCartItem();
        double price = cart.getPrice();
        Integer count = cartItem.get(book);
        if (count!=null){
            cartItem.remove(book);
            price=price-count*book.getPrice();
            cart.setPrice(price);
            return cart;
        }else {
            return cart;
        }
    }
}
