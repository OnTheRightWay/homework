package com.nys.bookstore.user.service.exception;

public class StateIsTrueException extends UserException {
    @Override
    public String getMessage() {
        return "用户已激活";
    }
}
