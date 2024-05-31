package com.example.paysystem.controller;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.UserRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRESTController {

    private final UserRepo userRepo;
    @Autowired
    public UserRESTController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @ApiOperation(value = "Get all users with role ROLE_USER")
    @GetMapping("/users/role/user")
    public List<User> getUsersWithRoleUser() {
        return userRepo.findByRolesContains(Role.ROLE_USER);
    }
}