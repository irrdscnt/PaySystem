package com.example.paysystem.service;

import com.example.paysystem.entity.Payment;
import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.Status;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.PaymentRepository;
import com.example.paysystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    private UserRepo userRepository;

    public void requestPayment(Double amount, String apiKey) {
        // Поиск пользователя по API ключу
        User seller = userRepository.findByApiKey(apiKey).orElseThrow(() -> new RuntimeException("User not found"));

        // Проверка на роль продавца
        if (!seller.getRoles().contains(Role.ROLE_SELLER)) {
            throw new RuntimeException("User is not a seller");
        }

        // Поиск пользователя-покупателя (с ролью ROLE_USER)
        List<User> buyers = userRepository.findByRolesContains(Role.ROLE_USER);

        // Предположим, что выбирается первый попавшийся покупатель
        if (!buyers.isEmpty()) {
            User buyer = buyers.get(0);

            // Создание нового платежа
            Payment payment = new Payment();
            payment.setAmount(amount);
            payment.setUser(buyer);
            payment.setStatus(Status.PENDING);

            // Сохранение платежа в репозитории
            paymentRepository.save(payment);
        } else {
            throw new RuntimeException("No buyers found");
        }
    }


    public List<Payment> getPendingPaymentsForUser(Long userId, String apiKey) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Проверка на совпадение API ключа
        if (!user.getApiKey().equals(apiKey)) {
            throw new RuntimeException("Invalid API key for user");
        }

        return paymentRepository.findByUserAndStatus(user, Status.PENDING);
    }
}

