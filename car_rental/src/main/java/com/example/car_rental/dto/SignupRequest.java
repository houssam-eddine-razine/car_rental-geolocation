package com.example.car_rental.dto;


import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class SignupRequest {

    private String email;
    private String name;
    private String password;

}
