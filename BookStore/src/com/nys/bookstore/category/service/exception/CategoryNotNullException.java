package com.nys.bookstore.category.service.exception;

public class CategoryNotNullException extends CategoryException{
    @Override
    public String getMessage() {
        return "该分组不为空";
    }
}
