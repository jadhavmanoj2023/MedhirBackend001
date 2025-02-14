package com.example.medhirBackend001.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private Set<String> roles;
}

