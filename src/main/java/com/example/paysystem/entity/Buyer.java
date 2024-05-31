package com.example.paysystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Table(name = "buyer")
@Entity
@Getter
@Setter
@Data
public class Buyer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private boolean active;

    private String name;

    @Column(length = 1000)
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime dateOfCreated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Column(unique = true)
    private int account;

    private Double balance;

    private String apiKey;


    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
        Random random = new Random();
        this.account = 100000 + random.nextInt(900000);
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(64);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 64; i++) {
            sb.append(alphanumeric.charAt(secureRandom.nextInt(alphanumeric.length())));
        }
        this.apiKey = sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
