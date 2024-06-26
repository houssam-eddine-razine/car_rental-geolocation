package com.example.car_rental.dto;

import com.example.car_rental.enums.UserRole;
import lombok.Data;
import lombok.Setter;

@Data
public class UserDto {

    @Setter
    private long id;

    private String name;

    private String email;

    private UserRole userRole;


}
