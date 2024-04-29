package com.example.car_rental.dto;

import com.example.car_rental.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private UserRole userRole;


    public void setId(Long id) {
        this.id = id;
    }
}
