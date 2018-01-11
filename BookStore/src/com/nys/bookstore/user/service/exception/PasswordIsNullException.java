package com.nys.bookstore.user.service.exception;

public class PasswordIsNullException extends RegisterException {
    @Override
    public String getMessage() {
        return "密码不能为空";
    }
}
