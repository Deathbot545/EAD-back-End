package com.example.Payment_Service.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository
       extends JpaRepository<Payment, Integer> {
}
