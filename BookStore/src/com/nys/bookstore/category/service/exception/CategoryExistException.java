package com.nys.bookstore.category.service.exception;

public class CategoryExistException extends CategoryException {
    @Override
    public String getMessage() {
        return "分组已存在";
    }
}
