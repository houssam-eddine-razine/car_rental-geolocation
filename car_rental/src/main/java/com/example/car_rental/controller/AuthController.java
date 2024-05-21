package com.example.car_rental.controller;


//import com.example.car_rental.dto.AuthenticationRequest;
//import com.example.car_rental.dto.AuthenticationResponse;
//import com.example.car_rental.dto.SignupRequest;
//import com.example.car_rental.dto.UserDto;
//import com.example.car_rental.entity.User;
import com.example.car_rental.dto.AuthenticationRequest;
import com.example.car_rental.dto.AuthenticationResponse;
import com.example.car_rental.dto.SignupRequest;
import com.example.car_rental.dto.UserDto;
import com.example.car_rental.entity.User;
import com.example.car_rental.repository.UserRepository;
import com.example.car_rental.services.auth.AuthService;
//import com.example.car_rental.services.jwt.UserService;
import com.example.car_rental.services.jwt.UserService;
import com.example.car_rental.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
//import org.apache.coyote.Res ponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
           return new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null){ return new ResponseEntity<>
                ("Customer not created, come again later", HttpStatus.BAD_REQUEST);}
        else {return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);}
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;
    }


}
