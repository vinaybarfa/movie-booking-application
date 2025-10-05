package com.vinay.barfa.Movie.Booking.Application.controller;

import com.vinay.barfa.Movie.Booking.Application.dto.BookingDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Booking;
import com.vinay.barfa.Movie.Booking.Application.role.BookingStatus;
import com.vinay.barfa.Movie.Booking.Application.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/addBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.addBooking(bookingDto));
    }

    @GetMapping("/getUserBookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllUserBookings(id));
    }

    @GetMapping("/getShowBookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllShowBookings(id));
    }

    @PutMapping("{id}/confirm") // Once the payment method is down then we conform the booking
    public ResponseEntity<Booking> confirmBooking(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.confirmBooking(id));
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.cancelBooking(id));
    }

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@RequestParam BookingStatus bookingStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingsByStatus(bookingStatus));
    }


}
