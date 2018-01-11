package com.nys.bookstore.user.service.exception;

public class EmailIsNullException extends RegisterException {
    @Override
    public String getMessage() {
        return "邮箱不能为空";
    }
}
