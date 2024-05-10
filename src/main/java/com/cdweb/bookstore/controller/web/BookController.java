package com.cdweb.bookstore.controller.web;

import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping("/hot-book")
    public List<BookDTO> findHotBook() {
        return bookService.findHotBook(true, true);
    }

    @GetMapping("/new-book")
    public List<BookDTO> findNewBook() {
        return bookService.findNewBook(true, true);
    }
}
