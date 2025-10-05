package com.vinay.barfa.Movie.Booking.Application.model;

import com.vinay.barfa.Movie.Booking.Application.role.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private LocalDateTime bookingTime;
    private Integer numberOfSeats;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ElementCollection(fetch = FetchType.EAGER)  // Using @ElementCollection => spring will create a suppurate table
    @CollectionTable(name = "booking_seat_number")
    private List<String> seatNumbers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
