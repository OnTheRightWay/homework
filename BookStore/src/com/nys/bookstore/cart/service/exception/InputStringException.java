package com.nys.bookstore.cart.service.exception;

public class InputStringException extends Exception {
    @Override
    public String getMessage() {
        return "输入错误";
    }
}
