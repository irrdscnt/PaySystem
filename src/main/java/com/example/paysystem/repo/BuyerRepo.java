package com.example.paysystem.repo;

import com.example.paysystem.entity.Buyer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BuyerRepo extends JpaRepository<Buyer,Long> {
    Buyer findByEmail(String email);
    Optional<Buyer> findByApiKey(String apiKey);
}
