package com.nys.bookstore.user.service.exception;

public class EmailIsNotFormatException extends RegisterException {
    @Override
    public String getMessage() {
        return "邮箱格式错误";
    }
}
