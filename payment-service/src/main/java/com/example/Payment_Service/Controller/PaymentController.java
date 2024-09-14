package com.example.Payment_Service.Controller;

import com.example.Payment_Service.Model.CreditCardInfo;
import com.example.Payment_Service.Model.Payment;
import com.example.Payment_Service.Service.CreditCardService;
import com.example.Payment_Service.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CreditCardService creditCardService;

    // Process a payment
    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.processPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    // Retrieve payment details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/transactions")
    public ResponseEntity<List<Payment>> getAllTransactions() {
        List<Payment> payments = paymentService.getAllTransactions();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping("/save-credit-card")
    public ResponseEntity<String> saveCreditCardInfo(@RequestBody CreditCardInfo creditCardInfo) {
        creditCardService.saveCreditCardInfo(creditCardInfo);
        return ResponseEntity.ok("Credit card information saved successfully");
    }

    @GetMapping("/credit-card/{userId}")
    public ResponseEntity<CreditCardInfo> getCreditCardInfo(@PathVariable Long userId) {
        return creditCardService.getCreditCardInfo(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
