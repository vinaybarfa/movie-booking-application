package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.dto.LoginRequestDTO;
import com.vinay.barfa.Movie.Booking.Application.dto.LoginResponseDTO;
import com.vinay.barfa.Movie.Booking.Application.dto.RegisterRequestDTO;
import com.vinay.barfa.Movie.Booking.Application.jwt.JwtService;
import com.vinay.barfa.Movie.Booking.Application.model.User;
import com.vinay.barfa.Movie.Booking.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// First NormalUser and AdminUser need to 'register'(signup) before the 'login'

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User is already register");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        User user = modelMapper.map(registerRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Admin is already register");
        }

        User user = modelMapper.map(registerRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        return userRepository.save(user);
    }

    public LoginResponseDTO loginNormalAndAdminUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getPassword(),
                        loginRequestDTO.getUsername()
                )
        );

        String token = jwtService.generateJwtToken(user);

        return LoginResponseDTO.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }


}
