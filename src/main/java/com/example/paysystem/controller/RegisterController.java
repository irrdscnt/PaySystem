package com.example.paysystem.controller;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.User;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping
@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final BuyerService buyerService;

    @GetMapping("/regBuyer")
    public String regBuyer() {
        return "reg/regClient";
    }

    @GetMapping("/regSeller")
    public String regSeller() {
        return "reg/regSeller";
    }

    @GetMapping("/login")
    public String login() {
        return "reg/login";
    }

    @PostMapping("/regBuyer")
    public String createBuyer(Buyer buyer, Model model) {
        if (!buyerService.createBuyer(buyer)) {
            model.addAttribute("message", "User already exists! Please ");
            return "reg/regClient";
        }
        return "redirect:/login";
    }

    @PostMapping("/regSeller")
    public String createSeller(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("message", "User already exists! Please ");
            return "reg/regSeller";
        }
        return "redirect:/login";
    }
}

