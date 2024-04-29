package com.example.car_rental.services.auth;

import com.example.car_rental.dto.SignupRequest;
import com.example.car_rental.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
