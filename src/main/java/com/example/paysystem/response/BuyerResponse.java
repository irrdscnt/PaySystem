package com.example.paysystem.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerResponse {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
