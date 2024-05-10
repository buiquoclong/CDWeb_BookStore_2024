package com.cdweb.bookstore.controller.web;

import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    //chuyen huong qua trang chi tiet
    @GetMapping("/chi-tiet")
    public ModelAndView detail(@RequestParam(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("web/detail.html");
        BookDTO bookDb = bookService.findById(id);
        mav.addObject("list", bookService.findByCategoryIdAnQuantityGreaterThan(bookDb.getCategory().getCategoryID(), 50)); // lay ds co cung category va co so luong > 50
        return mav;
    }

    @GetMapping("/getDetailBook")
    public BookDTO getDetail(@RequestParam(name = "id") int id) {
        return bookService.findById(id);
    }

}
