package com.example.Payment_Service.Service;

import com.example.Payment_Service.Model.CreditCardInfo;
import com.example.Payment_Service.Model.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCardInfo saveCreditCardInfo(CreditCardInfo creditCardInfo) {

        return creditCardRepository.save(creditCardInfo);
    }

    public Optional<CreditCardInfo> getCreditCardInfo(Long userId) {
        return creditCardRepository.findByUserId(userId);
    }
}
