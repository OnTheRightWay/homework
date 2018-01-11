package com.nys.service.exception;

public class UsernameNotFormatException extends RegisterException {
    @Override
    public String getMessage() {
        return "用户名格式错误";
    }
}
