package com.cdweb.bookstore.service.impl;

import com.cdweb.bookstore.converter.BookConverter;
import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.entities.BookEntity;
import com.cdweb.bookstore.repository.BookRepository;
import com.cdweb.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements IBookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private BookConverter bookConverter;

    @Override
    public List<BookDTO> findHotBook(boolean isActive, boolean isHot) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findFirst8ByActiveAndHotOrderByIdDesc(isActive, isHot)) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findNewBook(boolean isActive, boolean isNew) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findFirst8ByActiveAndNewsOrderByIdDesc(isActive, isNew)) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public BookDTO findById(int id) {
        return bookConverter.toDTO(bookRepo.findById(id).get());
    }


    @Override
    public List<BookDTO> findByCategoryIdAnQuantityGreaterThan(int categoryId, int quantity) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findFirst5ByCategoryCategoryIDAndQuantitySoldGreaterThan(categoryId, quantity)) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

}
