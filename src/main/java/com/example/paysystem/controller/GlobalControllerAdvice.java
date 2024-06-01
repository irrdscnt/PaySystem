package com.example.paysystem.controller;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.User;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {
    private final UserService userService;
    private final BuyerService buyerService;

    @ModelAttribute("currentUser")
    public User getUserProfile(
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser != null)
            return (User) userService.findUserByEmail(currentUser.getUsername());
        return null;
    }

    @ModelAttribute("currentBuyer")
    public Buyer getBuyerProfile(
            @AuthenticationPrincipal UserDetails currentBuyer
    ) {
        if (currentBuyer != null)
            return (Buyer) buyerService.findUserByEmail(currentBuyer.getUsername());
        return null;
    }

}
