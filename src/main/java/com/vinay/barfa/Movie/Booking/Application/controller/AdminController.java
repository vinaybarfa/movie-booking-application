package com.vinay.barfa.Movie.Booking.Application.controller;

import com.vinay.barfa.Movie.Booking.Application.dto.RegisterRequestDTO;
import com.vinay.barfa.Movie.Booking.Application.model.User;
import com.vinay.barfa.Movie.Booking.Application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {          // for 'Admin' user

    private final AuthenticationService authenticationService;

    @PostMapping("/registerAdminUser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.registerAdminUser(registerRequestDTO));
    }

/*    @PostMapping("/loginAdmin")
    public ResponseEntity<LoginResponseDTO> loginAdminUser(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginAdminUser(loginRequestDTO));
    }*/

}
