package com.example.Payment_Service.Service;

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
