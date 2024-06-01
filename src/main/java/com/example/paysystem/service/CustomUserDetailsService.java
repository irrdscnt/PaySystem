package com.example.paysystem.service;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.repo.BuyerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    private final BuyerRepo buyerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), Collections.emptyList());
        }

        Buyer buyer = buyerRepo.findByEmail(email);
        if (buyer != null) {
            return new org.springframework.security.core.userdetails.User(
                    buyer.getEmail(), buyer.getPassword(), Collections.emptyList());
        }

        throw new UsernameNotFoundException("User or Buyer not found with email: " + email);
    }
}

