package com.example.finalproject_clinic.controller.security;

import com.example.finalproject_clinic.persistence.entity.security.User;
import com.example.finalproject_clinic.service.impl.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/403")
    public String error() {
        return "<h1>Access denied - 403</h1>";
    }
}
