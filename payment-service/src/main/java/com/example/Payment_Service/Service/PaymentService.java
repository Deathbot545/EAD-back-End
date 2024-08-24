package com.example.Payment_Service.Service;

import com.example.Payment_Service.Model.Payment;
import com.example.Payment_Service.Model.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Process a payment
    public Payment processPayment(Payment payment) {
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return paymentRepository.save(payment);
    }

    // Retrieve payment details by ID
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    // List all transactions
    public List<Payment> getAllTransactions() {
        return paymentRepository.findAll();
    }
}
