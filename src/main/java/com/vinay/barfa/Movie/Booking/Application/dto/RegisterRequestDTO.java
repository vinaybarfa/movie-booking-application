package com.vinay.barfa.Movie.Booking.Application.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String username , email, password;
}
