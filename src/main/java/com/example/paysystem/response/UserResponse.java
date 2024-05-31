package com.example.paysystem.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}