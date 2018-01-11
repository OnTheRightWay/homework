package com.nys.bookstore.user.service.exception;

public class CodeErrorException extends UserException {
    @Override
    public String getMessage() {
        return "激活码错误";
    }
}
