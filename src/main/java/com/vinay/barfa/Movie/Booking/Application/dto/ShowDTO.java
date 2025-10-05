package com.vinay.barfa.Movie.Booking.Application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {

    private LocalDateTime showTime;
    private Double price;
    private Integer movieId;
    private Integer theaterId;


}
