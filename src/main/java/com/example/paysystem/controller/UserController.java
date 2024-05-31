package com.example.paysystem.controller;

import com.example.paysystem.entity.User;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile/{user}")
    public String profilePage(@PathVariable("user") String name, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile/userProfile";
    }
}
