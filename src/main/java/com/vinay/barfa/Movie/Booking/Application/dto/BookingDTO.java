package com.vinay.barfa.Movie.Booking.Application.dto;

import com.vinay.barfa.Movie.Booking.Application.role.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {

    private Integer numberOfSeats;
    private Double price;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;
    private List<String> seatNumbers;
    private Integer showId;
    private Integer userId;


}
