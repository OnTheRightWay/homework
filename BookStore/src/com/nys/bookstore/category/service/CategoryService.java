package com.nys.bookstore.category.service;

import com.nys.bookstore.book.domain.Book;
import com.nys.bookstore.category.dao.CategoryDao;
import com.nys.bookstore.category.domain.Category;
import com.nys.bookstore.category.service.exception.CategoryException;
import com.nys.bookstore.category.service.exception.CategoryExistException;
import com.nys.bookstore.category.service.exception.CategoryNotNullException;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    public void add(Category category) throws CategoryException{
        if (categoryDao.queryByCname(category.getCname())!=null){
            throw new CategoryExistException();
        }
        categoryDao.insert(category);
    }
    public void deleteByCid(String cname) throws CategoryException{
        List<Book> books = categoryDao.queryBookByCname(cname);
        if (books.size()>0){
            throw new CategoryNotNullException();
        }
        categoryDao.deleteByCname(cname);
    }
    public void edit(String id,Category category)throws CategoryException{
        if (categoryDao.queryByCname(category.getCname())!=null){
            throw new CategoryExistException();
        }
        categoryDao.updateByCid(id,category);
    }
}
