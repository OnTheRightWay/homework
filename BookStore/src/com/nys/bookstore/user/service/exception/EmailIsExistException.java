package com.nys.bookstore.user.service.exception;

public class EmailIsExistException extends RegisterException {
    @Override
    public String getMessage() {
        return "该邮箱已被注册";
    }
}
