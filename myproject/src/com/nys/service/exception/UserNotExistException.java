package com.nys.service.exception;

public class UserNotExistException extends LoginException {
    @Override
    public String getMessage() {
        return "用户不存在";
    }
}
