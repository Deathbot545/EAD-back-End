package com.example.Payment_Service.Controller;

import com.example.Payment_Service.Model.CreditCardInfo;
import com.example.Payment_Service.Service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {


    @Autowired
    private CreditCardService creditCardService;

    // Process a payment

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
