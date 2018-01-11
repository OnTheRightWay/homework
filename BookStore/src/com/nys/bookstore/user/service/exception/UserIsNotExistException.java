package com.nys.bookstore.user.service.exception;

public class UserIsNotExistException extends LoginException {
    @Override
    public String getMessage() {
        return "用户名不存在";
    }
}
