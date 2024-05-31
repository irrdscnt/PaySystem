package com.example.paysystem.service;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.repo.BuyerRepo;
import com.example.paysystem.response.BuyerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerRepo buyerRepo;
    private final PasswordEncoder passwordEncoder;

    public void banUser(Long id) {
        Buyer user = buyerRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        buyerRepo.save(user);
    }

    public boolean createUser(Buyer user) {
        String email = user.getEmail();
        if (buyerRepo.findByEmail(email) != null) return false;
//        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new Buyer with email: {}", email);
        buyerRepo.save(user);
        return true;
    }

    public List<Buyer> list() {
        return buyerRepo.findAll();
    }

    public Buyer findUserByEmail(String email){
        return buyerRepo.findByEmail(email);
    }

}
