package com.example.paysystem.dto;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private Boolean active;
    private String name;
    private Set<Role> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.active = user.isActive();
        this.name = user.getName();
        this.roles = user.getRoles();
    }

}
