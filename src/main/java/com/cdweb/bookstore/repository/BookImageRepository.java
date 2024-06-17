package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.BookImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImageEntity, Integer> {
}
