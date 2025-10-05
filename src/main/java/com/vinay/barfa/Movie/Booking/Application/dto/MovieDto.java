package com.vinay.barfa.Movie.Booking.Application.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {
    private String name;
    private String description;
    private String genre;   // genre means categories
    private String language;
    private Integer duration;
    private LocalDate releaseDate;
}
