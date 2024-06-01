package com.example.paysystem.service;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;
import com.example.paysystem.repo.UserRepo;
import com.example.paysystem.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void banUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepo.save(user);
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setBalance(0.0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepo.save(user);
        return true;
    }

    public List<User> list() {
        return userRepo.findAll();
    }


    public User findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
    public User findUserByName(String name){
        return userRepo.findByName(name);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }


    public boolean createUserApi(UserResponse userResponse) {
        String email = userResponse.getEmail();
        if (userRepo.findByEmail(email) != null) return false;

        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(userResponse.getPhoneNumber());
        user.setName(userResponse.getName());
        user.setPassword(passwordEncoder.encode(userResponse.getPassword()));
        user.setRoles(Collections.singleton(userResponse.getRoles()));
        user.setActive(true);

        userRepo.save(user);
        return true;
    }

}
