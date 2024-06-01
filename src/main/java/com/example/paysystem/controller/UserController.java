package com.example.paysystem.controller;

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
public class UserController {
    private final UserService userService;
    private final BuyerService buyerService;

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", userService.findUserByName(user.getName()));
        model.addAttribute("buyer", buyerService.list());
        return "user";
    }

//    @PostMapping("/requestMoney")
//    public String requestMoney(@RequestParam("selectBuyer") Long buyerId,
//                               @ModelAttribute("pay") Payment payment{
//
//        return "redirect:/";
//    }
}