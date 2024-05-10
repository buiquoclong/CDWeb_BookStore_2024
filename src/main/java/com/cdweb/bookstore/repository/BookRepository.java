package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findFirst8ByActiveAndHotOrderByIdDesc(boolean isActive, boolean isHot);

    List<BookEntity> findFirst8ByActiveAndNewsOrderByIdDesc(boolean isActive, boolean isNew);

}
