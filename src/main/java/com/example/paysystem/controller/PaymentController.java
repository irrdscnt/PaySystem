package com.example.paysystem.controller;
import com.example.paysystem.entity.*;
import com.example.paysystem.repo.BuyerRepo;
import com.example.paysystem.repo.PaymentRepository;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.request.PaymentRequest;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class PaymentController {
    private final UserRepo userRepo;
    private final PaymentRepository paymentRepository;
    private final BuyerRepo buyerRepo;

    private final PaymentService paymentService;
    private final BuyerService buyerService;

    @Autowired
    public PaymentController(UserRepo userRepo, PaymentRepository paymentRepository, BuyerRepo buyerRepo, PaymentService paymentService, BuyerService buyerService) {
        this.userRepo = userRepo;
        this.paymentRepository = paymentRepository;
        this.buyerRepo = buyerRepo;
        this.paymentService = paymentService;
        this.buyerService = buyerService;
    }


    @ApiOperation(value = "Request payment from a buyer")
    @PostMapping("/payments/request")
    public void requestPayment(@RequestBody PaymentRequest paymentRequest) {
        Double amount = paymentRequest.getAmount();
        String apiKey = paymentRequest.getApiKey();
        Long buyerId = paymentRequest.getBuyerId();
        Optional<User> sellerOptional = userRepo.findByApiKey(apiKey);
        User seller = sellerOptional.orElseThrow(() -> new RuntimeException("Seller not found"));

        if (!seller.getRoles().contains(Role.ROLE_SELLER)) {
            throw new RuntimeException("User is not a seller");
        }

        Buyer buyer = buyerRepo.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setUser(seller);
        payment.setBuyer(buyer);
        payment.setStatus(Status.PENDING);

        paymentRepository.save(payment);
    }
    @ApiOperation(value = "Process payment by the buyer")
    @PostMapping("/payments/process")
    public ResponseEntity<String> processPayment(
            @ApiParam(value = "ID of the payment") @RequestParam Long paymentId,
            @ApiParam(value = "API key of the buyer") @RequestParam String apiKey) {
        try {
            paymentService.processPayment(paymentId, apiKey);
            return ResponseEntity.status(HttpStatus.OK).body("Payment processed successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}