package com.example.Order_Service.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
         extends JpaRepository<Order, Integer> {
}
