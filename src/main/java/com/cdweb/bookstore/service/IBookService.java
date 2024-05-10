package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.BookDTO;

import java.util.List;

public interface IBookService {
    public List<BookDTO> findHotBook(boolean isActive, boolean isHot);

    public List<BookDTO> findNewBook(boolean isActive, boolean isNew);
    public BookDTO findById(int id);

    public List<BookDTO> findByCategoryIdAnQuantityGreaterThan(int categoryId, int quantity);
}
