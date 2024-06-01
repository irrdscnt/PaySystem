package com.example.paysystem.controller;

import com.example.paysystem.dto.PaymentDTO;
import com.example.paysystem.entity.Payment;
import com.example.paysystem.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ForHtmlConnectController {

    private final PaymentService paymentService;

    public ForHtmlConnectController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/transactions-all")
    public String getTransactions(Model model) {
        List<Payment> payments = paymentService.listAllPayments();
        model.addAttribute("payments", payments);
        return "transactions";
    }
}