package com.example.paysystem.repo;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.Payment;
import com.example.paysystem.entity.Status;
import com.example.paysystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByUserAndStatus(User user, Status status);
    List<Payment> findByBuyerAndStatus(Buyer buyer, Status status);

}
