package com.example.Payment_Service.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCardInfo, Long> {
    Optional<CreditCardInfo> findByUserId(Long userId);
}