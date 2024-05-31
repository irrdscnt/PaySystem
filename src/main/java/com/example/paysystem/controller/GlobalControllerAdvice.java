package com.example.paysystem.controller;

import com.example.paysystem.entity.User;
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

    @ModelAttribute("currentUser")
    public User getUserProfile(
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        if (currentUser != null)
            return (User) userService.findUserByEmail(currentUser.getUsername());
        return null;
    }

}
