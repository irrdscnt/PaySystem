package com.example.paysystem.service;

import com.example.paysystem.entity.*;
import com.example.paysystem.repo.BuyerRepo;
import com.example.paysystem.repo.PaymentRepository;
import com.example.paysystem.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepo userRepo;
    private  final BuyerRepo buyerRepo;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepo userRepo, BuyerRepo buyerRepo) {
        this.paymentRepository = paymentRepository;
        this.userRepo = userRepo;
        this.buyerRepo = buyerRepo;
    }

    @Autowired
    private UserRepo userRepository;

    public void requestPayment(Double amount, String apiKey) {
        User seller = userRepository.findByApiKey(apiKey).orElseThrow(() -> new RuntimeException("User not found"));

        if (!seller.getRoles().contains(Role.ROLE_SELLER)) {
            throw new RuntimeException("User is not a seller");
        }

        List<User> buyers = userRepository.findByRolesContains(Role.ROLE_USER);

        if (!buyers.isEmpty()) {
            User buyer = buyers.get(0);

            Payment payment = new Payment();
            payment.setAmount(amount);
            payment.setUser(buyer);
            payment.setStatus(Status.PENDING);

            paymentRepository.save(payment);
        } else {
            throw new RuntimeException("No buyers found");
        }
    }


    public List<Payment> getPendingPaymentsForUser(Long userId, String apiKey) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getApiKey().equals(apiKey)) {
            throw new RuntimeException("Invalid API key for user");
        }

        return paymentRepository.findByUserAndStatus(user, Status.PENDING);
    }

    @Transactional
    public void processPayment(Long paymentId, String apiKey) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));

        Buyer buyer = buyerRepo.findByApiKey(apiKey).orElseThrow(() -> new RuntimeException("Buyer not found"));

        if (!payment.getBuyer().equals(buyer)) {
            throw new RuntimeException("Payment does not belong to the buyer");
        }

        if (buyer.getBalance() < payment.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        buyer.setBalance(buyer.getBalance() - payment.getAmount());
        User seller = payment.getUser();
        seller.setBalance(seller.getBalance() + payment.getAmount());

        payment.setStatus(Status.SUCCESS);

        buyerRepo.save(buyer);
        userRepo.save(seller);
        paymentRepository.save(payment);
    }
}


