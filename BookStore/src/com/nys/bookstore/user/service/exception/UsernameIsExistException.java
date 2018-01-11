package com.nys.bookstore.user.service.exception;

public class UsernameIsExistException extends RegisterException {
    @Override
    public String getMessage() {
        return "用户名已被注册";
    }
}
