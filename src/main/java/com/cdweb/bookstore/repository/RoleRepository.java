package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    public RoleEntity findByName(String name);
@Query(value = "select r.name from role r join roleuser ru on r.roleID=ru.roleID where ru.userID=?1",nativeQuery = true)
    public List<String> findAllByUserID(int id);
}
