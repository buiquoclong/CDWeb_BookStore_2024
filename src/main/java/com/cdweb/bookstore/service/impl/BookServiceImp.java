package com.cdweb.bookstore.service.impl;

import com.cdweb.bookstore.converter.BookConverter;
import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.entities.BookEntity;
import com.cdweb.bookstore.repository.BookRepository;
import com.cdweb.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    @Override
    public List<BookDTO> findByCategoryCode(String categoryCode, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findByCategoryCode(categoryCode, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAllByAuthorCode(String code, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByAuthorAuthorCode(code, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAll(Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();

        //hàm findAll(pageable) sẽ trả về Page<BookEntity>, để chuyển Page thành List thì dùng hàm getContent()
        for (BookEntity b : bookRepo.findAll(pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAllHotBook(boolean isActive, boolean isHot, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByActiveAndHot(isActive, isHot, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAllNewBook(boolean isActive, boolean isNew, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByActiveAndNews(isActive, isNew, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAllContainTitle(String title, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByTitleContains(title, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findByPriceBetween(int from, int to, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByPriceBetween(from, to, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findByPriceGreaterThan(int from, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByPriceGreaterThan(from, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public int countByCategory(String code) {
        return bookRepo.countAllByCategoryCode(code);
    }

    @Override
    public int countByAuthorCode(String code) {
        return bookRepo.countAllByAuthorAuthorCode(code);
    }

    @Override
    public int countAllByActive(boolean isActive) {
        return bookRepo.countAllByActive(isActive);
    }

    @Override
    public int countAllByHot(boolean isActive, boolean isHot) {
        return bookRepo.countAllByActiveAndHot(isActive, isHot);
    }

    @Override
    public int countAllByNews(boolean isActive, boolean isNew) {
        return bookRepo.countAllByActiveAndNews(isActive, isNew);
    }

    @Override
    public int countAllByTitleContains(String title) {
        return bookRepo.countAllByTitleContains(title);
    }

    @Override
    public int countAllByPriceBetween(int from, int to) {
        return bookRepo.countAllByPriceBetween(from, to);
    }

    @Override
    public int countAllByPriceGreaterThan(int from) {
        return bookRepo.countAllByPriceGreaterThan(from);
    }

    @Override
    public List<BookDTO> findAllByActiveAndDicount(boolean active, double from, double to, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByActiveAndDiscountPercentBetween(true, from, to, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public int countAllByActiveAndDiscount(boolean active, double from, double to) {
        return bookRepo.countAllByActiveAndDiscountPercentBetween(active, from, to);
    }

}
