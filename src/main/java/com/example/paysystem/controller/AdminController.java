package com.example.paysystem.controller;

import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final BuyerService buyerService;

    @GetMapping("/admin")
    public String adminPanel(Model model){
        return "admin";
    }

    @GetMapping("/admin/clients")
    public String adminPanelClient(Model model){
        model.addAttribute("user", userService.list());
        model.addAttribute("buyer", buyerService.list());
        return "allUsers";
    }

}
