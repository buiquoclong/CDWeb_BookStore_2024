package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    //tìm tất cả saách theo danh mục, phân trang
    public Page<BookEntity> findByCategoryCode(String code, Pageable pageable);

    public Page<BookEntity> findAllByAuthorAuthorCode(String code, Pageable pageable);


    List<BookEntity> findFirst8ByActiveAndHotOrderByIdDesc(boolean isActive, boolean isHot);

    List<BookEntity> findFirst8ByActiveAndNewsOrderByIdDesc(boolean isActive, boolean isNew);

    public List<BookEntity> findFirst5ByCategoryCategoryIDAndQuantitySoldGreaterThan(int categoryid, int quantity);

    public Page<BookEntity> findAllByActiveAndHot(boolean isActive, boolean isHot, Pageable pageable);

    public Page<BookEntity> findAllByActiveAndNews(boolean isActive, boolean isNew, Pageable pageable);

    public Page<BookEntity> findAllByTitleContains(String title, Pageable pageable);

    public Page<BookEntity> findAllByPriceBetween(int from, int to, Pageable pageable);

    public Page<BookEntity> findAllByPriceGreaterThan(int from, Pageable pageable);

    List<BookEntity> findAllByActiveAndTitleContains(boolean isActive, String title);

    Page<BookEntity> findAllByActiveAndDiscountPercentBetween(boolean active, double discountFrom, double discountTo, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update book set quantity_sold=:quantity where id=:id", nativeQuery = true)
    public void updateQuantity(@Param("quantity") int quantity,
                               @Param("id") int id);

    //đếm số sách theo danh mục
    public int countAllByCategoryCode(String code);

    public int countAllByAuthorAuthorCode(String code);

    //đếm tất cả sách
    public int countAllByActive(boolean isActive);

    public int countAllByActiveAndHot(boolean isActive, boolean isHot);

    public int countAllByActiveAndNews(boolean isActive, boolean isNew);

    public int countAllByTitleContains(String title);

    public int countAllByPriceBetween(int from, int to);

    public int countAllByPriceGreaterThan(int from);

    public int countAllByActiveAndDiscountPercentBetween(boolean active, double discountFrom, double discountTo);


}
