package com.example.paysystem.dto;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private Boolean active;
    private String name;

    public BuyerDTO(Buyer buyer) {
        this.id = buyer.getId();
        this.email = buyer.getEmail();
        this.phoneNumber = buyer.getPhoneNumber();
        this.active = buyer.isActive();
        this.name = buyer.getName();
    }
}
