package com.vinay.barfa.Movie.Booking.Application.dto;

import com.vinay.barfa.Movie.Booking.Application.role.ScreenType;
import lombok.Data;

@Data
public class TheaterDTO {
    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private ScreenType theaterScreenType;
}
