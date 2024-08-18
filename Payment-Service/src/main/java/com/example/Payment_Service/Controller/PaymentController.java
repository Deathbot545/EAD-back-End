package com.example.Payment_Service.Controller;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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

    // List all transactions
    @GetMapping("/transactions")
    public ResponseEntity<List<Payment>> getAllTransactions() {
        List<Payment> payments = paymentService.getAllTransactions();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
