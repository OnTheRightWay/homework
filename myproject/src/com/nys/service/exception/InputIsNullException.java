package com.nys.service.exception;

public class InputIsNullException extends LoginException {
    @Override
    public String getMessage() {
        return "用户名或密码为空";
    }
}
