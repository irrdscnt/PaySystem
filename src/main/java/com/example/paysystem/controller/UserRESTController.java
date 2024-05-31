package com.example.paysystem.controller;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.response.BuyerResponse;
import com.example.paysystem.response.UserResponse;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRESTController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final BuyerService buyerService;

    @Autowired
    public UserRESTController(UserRepo userRepo, UserService userService, BuyerService buyerService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.buyerService = buyerService;
    }

    @ApiOperation(value = "Get all users with role ROLE_USER")
    @GetMapping("/users/role/user")
    public List<User> getUsersWithRoleUser() {
        return userRepo.findByRolesContains(Role.ROLE_USER);
    }

//    @PostMapping("/registration/buyer")
//    public ResponseEntity<String> createBuyer(@RequestBody BuyerResponse buyer) {
//        if (!buyerService.createUser(buyer)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("Buyer already exists! Please use a different email.");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body("Buyer registered successfully.");
//    }
//    @PostMapping("/registration/user")
//    public ResponseEntity<String> createBuyer(@RequestBody UserResponse user) {
//        if (!userService.createUser(user)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("User already exists! Please use a different email.");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body("Buyer registered successfully.");
//    }


}
