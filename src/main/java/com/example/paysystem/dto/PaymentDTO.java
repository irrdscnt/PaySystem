package com.example.paysystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private Double amount;
    private String status;
    private UserDTO user;
    private LocalDateTime createdAt;
    private BuyerDTO buyer;

    public PaymentDTO(Long id, Double amount, String status, UserDTO user,LocalDateTime createdAt, BuyerDTO buyer) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.createdAt=createdAt;
        this.buyer = buyer;
    }
}
