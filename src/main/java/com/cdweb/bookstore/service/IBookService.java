package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.BookDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    public List<BookDTO> findHotBook(boolean isActive, boolean isHot);

    public List<BookDTO> findNewBook(boolean isActive, boolean isNew);
    public BookDTO findById(int id);

    public List<BookDTO> findByCategoryIdAnQuantityGreaterThan(int categoryId, int quantity);
    public List<BookDTO> findByCategoryCode(String categoryCode, Pageable pageable);

    public List<BookDTO> findAllByAuthorCode(String code, Pageable pageable);
    public List<BookDTO> findAll(Pageable pageable);
    public List<BookDTO> findAllHotBook(boolean isActive, boolean isHot, Pageable pageable);

    public List<BookDTO> findAllNewBook(boolean isActive, boolean isNew, Pageable pageable);
    public List<BookDTO> findAllContainTitle(String title, Pageable pageable);
    public List<BookDTO> findByPriceBetween(int from, int to, Pageable pageable);
    public List<BookDTO> findByPriceGreaterThan(int from, Pageable pageable);

    public List<String> autoCompleteTilte(String title);

    //count
    public int countByCategory(String code);

    public int countByAuthorCode(String code);

    public int countAllByActive(boolean isActive);

    public int countAllByHot(boolean isActive, boolean isHot);

    public int countAllByNews(boolean isActive, boolean isNew);

    public int countAllByTitleContains(String titles);

    public int countAllByPriceBetween(int from, int to);

    public int countAllByPriceGreaterThan(int from);
    public List<BookDTO> findAllByActiveAndDicount(boolean active, double discountFrom, double discountTo, Pageable pageable);
    public int countAllByActiveAndDiscount(boolean active, double discountFrom, double discountTo);

}
