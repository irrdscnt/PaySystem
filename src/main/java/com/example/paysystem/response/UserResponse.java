package com.example.paysystem.response;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
//    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Role roles;
}