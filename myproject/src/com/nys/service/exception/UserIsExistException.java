package com.nys.service.exception;

public class UserIsExistException extends RegisterException {
    @Override
    public String getMessage() {
        return "该用户已存在";
    }
}
