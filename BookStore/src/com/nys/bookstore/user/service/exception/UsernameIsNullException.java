package com.nys.bookstore.user.service.exception;

public class UsernameIsNullException extends RegisterException{
    @Override
    public String getMessage() {
        return "用户名不能为空";
    }
}
