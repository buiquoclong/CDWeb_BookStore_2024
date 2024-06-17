package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    public AuthorEntity findByAuthorID(int id);

    @Transactional
    @Modifying
    @Query(value = "update author set name=:name, author_code=:code, created_at=:createdAt, updated_at=:updatedAt where authorID=:id", nativeQuery = true)
    public void updateAuthor(@Param("name") String name,
                             @Param("code")String code,
                             @Param("createdAt") LocalDate createdAt,
                             @Param("updatedAt") LocalDate updatedAt,
                             @Param("id") int id);

    @Transactional
    @Modifying
    public void deleteByAuthorID(int id);
}
