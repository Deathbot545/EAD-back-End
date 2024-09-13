package com.example.Order_Service.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
         extends JpaRepository<Order, Integer> {

    List<Order> findByFreelancerId(Integer freelancerId);
    List<Order> findByBuyerId(Integer buyerId);
}
