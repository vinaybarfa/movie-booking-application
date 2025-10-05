package com.vinay.barfa.Movie.Booking.Application.controller;

import com.vinay.barfa.Movie.Booking.Application.dto.LoginRequestDTO;
import com.vinay.barfa.Movie.Booking.Application.dto.LoginResponseDTO;
import com.vinay.barfa.Movie.Booking.Application.dto.RegisterRequestDTO;
import com.vinay.barfa.Movie.Booking.Application.model.User;
import com.vinay.barfa.Movie.Booking.Application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {       // for register 'Normal User'

    private final AuthenticationService authenticationService;

    @PostMapping("/registerNormalUser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/loginNormalUser")
    public ResponseEntity<LoginResponseDTO> loginNormalAndAdminUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginNormalAndAdminUser(loginRequestDTO));
    }

    @GetMapping("/info")
    public ResponseEntity<String> getMessage(@RequestParam String info, @RequestParam Integer additionInfo) {
        return ResponseEntity.status(HttpStatus.OK).body("Hi from " + info + " with age " + additionInfo);
    }

}
