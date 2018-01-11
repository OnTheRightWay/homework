package com.nys.bookstore.order.service.exception;

public class OrderStateException extends OrderException {
    @Override
    public String getMessage() {
        return "请先处理订单";
    }
}
