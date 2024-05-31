package com.example.paysystem.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Double amount;
    private String apiKey;
    private Long userId;


}
