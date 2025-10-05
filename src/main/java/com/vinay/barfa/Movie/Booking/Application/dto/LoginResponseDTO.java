package com.vinay.barfa.Movie.Booking.Application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponseDTO {
    private int id;
    private String jwtToken;    // Using this jwtToken User can access the api
    private String username;
    private Set<String> roles;
}
