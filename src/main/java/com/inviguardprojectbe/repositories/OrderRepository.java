package com.inviguardprojectbe.repositories;

import com.inviguardprojectbe.Classes.IncomingOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<IncomingOrders, Long> {

    //List<IncomingOrders> findAll(Long userId);
}
