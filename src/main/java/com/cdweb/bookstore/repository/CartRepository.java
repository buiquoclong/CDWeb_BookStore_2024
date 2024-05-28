package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    public CartEntity findByUserUserIDAndBookId(int userId, int bookId);

    public List<CartEntity> findAllByUserUserID(int userId);

    public CartEntity findByUserUserID(int userId);

    public CartEntity getByCartID(int id);
}
