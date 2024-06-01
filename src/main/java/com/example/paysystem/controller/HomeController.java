package com.example.paysystem.controller;

import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final BuyerService buyerService;

    @GetMapping("/")
    public String getHome(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("buyer", buyerService.getBuyerByPrincipal(principal));
        return "index";
    }

    @GetMapping("/contact")
    public String getContact(Model model){
        return "contact";
    }

    @GetMapping("/about")
    public String getAbout(Model model){
        return "about";
    }
}
