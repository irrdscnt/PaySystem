package com.example.paysystem.controller;
import com.example.paysystem.entity.Payment;
import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.Status;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.PaymentRepository;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.request.PaymentRequest;
import com.example.paysystem.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PaymentController {
    private final UserRepo userRepo;
    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(UserRepo userRepo, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.userRepo = userRepo;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }


    @ApiOperation(value = "Request payment from a buyer")
    @PostMapping("/payments/request")
    public void requestPayment(@RequestBody PaymentRequest paymentRequest) {
        Double amount = paymentRequest.getAmount();
        String apiKey = paymentRequest.getApiKey();
        Long userId = paymentRequest.getUserId();
        // Поиск пользователя-продавца по API ключу
        Optional<User> sellerOptional = userRepo.findByApiKey(apiKey);
        User seller = sellerOptional.orElseThrow(() -> new RuntimeException("Seller not found"));

        // Проверка на роль продавца
        if (!seller.getRoles().contains(Role.ROLE_SELLER)) {
            throw new RuntimeException("User is not a seller");
        }

        User buyer = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        // Создание нового платежа
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setUser(buyer);
        payment.setStatus(Status.PENDING);

        paymentRepository.save(payment);
    }
}