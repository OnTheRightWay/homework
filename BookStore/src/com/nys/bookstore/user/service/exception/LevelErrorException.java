package com.nys.bookstore.user.service.exception;

public class LevelErrorException extends LoginException {
    @Override
    public String getMessage() {
        return "权限不够（该用户不是管理员？）";
    }
}
