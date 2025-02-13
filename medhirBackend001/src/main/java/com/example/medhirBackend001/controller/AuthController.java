package com.example.medhirBackend001.controller;


import com.example.medhirBackend001.dto.AuthRequest;
import com.example.medhirBackend001.dto.RegisterRequest;
import com.example.medhirBackend001.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request){
        System.out.println("Received Register Request: " + request);
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody AuthRequest request){
        System.out.println("Received Login Request: " + request);
        return ResponseEntity.ok(userService.login(request));
    }
}
