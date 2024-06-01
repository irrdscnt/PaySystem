package com.example.paysystem.controller;

import com.example.paysystem.entity.Buyer;
import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.BuyerRepo;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.request.LoginRequest;
import com.example.paysystem.response.BuyerResponse;
import com.example.paysystem.response.UserResponse;
import com.example.paysystem.service.BuyerService;
import com.example.paysystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "User Registration", tags = {"User Registration"})
public class UserRESTController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final BuyerService buyerService;
    private final BuyerRepo buyerRepo;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserRESTController(UserRepo userRepo, UserService userService, BuyerService buyerService, BuyerRepo buyerRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.buyerService = buyerService;
        this.buyerRepo = buyerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "Get all users ")
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    @ApiOperation(value = "Get all buyers ")
    @GetMapping("/buyers")
    public List<Buyer> getBuyers() {
        return buyerRepo.findAll();
    }
    @ApiOperation(value = "Register a new buyer")
    @PostMapping("/registration/buyer")
    public ResponseEntity<String> createBuyerApi(
            @ApiParam(value = "Buyer registration data", required = true) @RequestBody BuyerResponse user) {
        if (!buyerService.createBuyerApi(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Buyer already exists! Please use a different email.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Buyer registered successfully.");
    }

    @ApiOperation(value = "Register a new seller/admin")
    @PostMapping("/registration/user")
    public ResponseEntity<String> createUser(
            @ApiParam(value = "User registration data", required = true) @RequestBody UserResponse user) {
        if (!userService.createUserApi(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists! Please use a different email.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }
    @ApiOperation(value = "User and Buyer login")
    @PostMapping("/login-for-all")
    public ResponseEntity<String> login(
            @ApiParam(value = "Login credentials", required = true) @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Проверка пользователя (User)
        User user = userRepo.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // В этом месте вы можете создать токен доступа для пользователя и вернуть его
            return ResponseEntity.ok(user.getApiKey());
        }

        // Проверка покупателя (Buyer)
        Buyer buyer = buyerRepo.findByEmail(email);
        if (buyer != null && passwordEncoder.matches(password, buyer.getPassword())) {
            // В этом месте вы можете создать токен доступа для покупателя и вернуть его
            return ResponseEntity.ok(buyer.getApiKey());
        }

        // Если ни пользователь, ни покупатель не найдены или пароли не совпадают
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
    }


}
