package com.example.car_rental.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
