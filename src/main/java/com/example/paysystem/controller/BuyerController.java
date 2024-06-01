package com.example.paysystem.controller;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.User;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BuyerController {
    private final BuyerService buyerService;
    private final UserService userService;

    @GetMapping("/buyer/{buyer}")
    public String userInfo(@PathVariable("buyer") Buyer buyer, Model model){
        model.addAttribute("buyer", buyerService.findUserByEmail(buyer.getEmail()));
        model.addAttribute("user", userService.list());
        return "buyer";
    }
}
