package com.inviguardprojectbe.repositories;

import com.inviguardprojectbe.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

   // List<Order> findAll(Long userId);
}
