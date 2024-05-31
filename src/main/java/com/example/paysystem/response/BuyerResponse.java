package com.example.paysystem.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BuyerResponse {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
