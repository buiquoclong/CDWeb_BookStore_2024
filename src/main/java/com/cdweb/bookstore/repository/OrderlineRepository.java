package com.cdweb.bookstore.repository;

import com.cdweb.bookstore.entities.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlineRepository extends JpaRepository<OrderLineEntity, Integer> {
}
