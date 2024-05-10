package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryID(int id);

    @Transactional
    @Modifying
    void deleteByCategoryID(int id);

    List<CategoryEntity> findFirst10ByOrderByCategoryIDAsc();
    @Transactional
    @Modifying
    @Query(value = "update category set name=:name, code=:code, created_at=:createdAt, updated_at=:updatedAt where categoryID=:id", nativeQuery = true)
    void updateCategory(@Param("name") String name,
                               @Param("code") String code,
                               @Param("createdAt") LocalDate createdAt,
                               @Param("updatedAt") LocalDate updatedAt,
                               @Param("id") int id);
}
