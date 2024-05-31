package com.example.paysystem.response;

import com.example.paysystem.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserResponse {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Role roles;
}