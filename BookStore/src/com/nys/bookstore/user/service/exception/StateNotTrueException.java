package com.nys.bookstore.user.service.exception;

public class StateNotTrueException extends LoginException {
    @Override
    public String getMessage() {
        return "请激活";
    }
}
