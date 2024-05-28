package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByEmailIgnoreCaseAndIsEnableAndStatus(String email, boolean isEnable, boolean status);

    @Query(value = "select confirm_token from users where userID=?1", nativeQuery = true)
    public String getConfirmTokenById(int id);

    public UserEntity findByEmailIgnoreCase(String email);

    public UserEntity findByUserID(int id);

    @Transactional
    @Modifying
    @Query(value = "update users set username=:username, fullname=:fullname " +
            ", birthdate=:birthdate, gender=:gender, phone=:phone, updated_at=:modified where userID=:id", nativeQuery = true)
    public void updateUser(@Param(value = "id") int id,
                           @Param(value = "username") String username,
                           @Param(value = "fullname") String fullname,
                           @Param(value = "birthdate") LocalDate birthdate,
                           @Param(value = "gender") boolean gender,
                           @Param(value = "phone") String phone,
                           @Param(value = "modified") LocalDate modifiedAt);

    @Transactional
    @Modifying
    @Query(value = "update users set password=:pass where userID=:id", nativeQuery = true)
    public void updatePass(@Param(value = "pass") String newPass,
                           @Param(value = "id") int id);

    @Transactional
    @Modifying
    public void deleteByUserID(int id);

}
