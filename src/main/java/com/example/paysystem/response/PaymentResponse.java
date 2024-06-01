package com.example.paysystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PaymentResponse {
    private Long id;
    private int amount;
    private String status;
    private UserResponse user;
    private BuyerResponse buyer;

}