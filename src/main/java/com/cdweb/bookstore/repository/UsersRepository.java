package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByEmailIgnoreCaseAndIsEnableAndStatus(String email, boolean isEnable, boolean status);
}
