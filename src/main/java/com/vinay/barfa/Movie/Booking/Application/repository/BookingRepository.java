package com.vinay.barfa.Movie.Booking.Application.repository;

import com.vinay.barfa.Movie.Booking.Application.model.Booking;
import com.vinay.barfa.Movie.Booking.Application.role.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<List<Booking>> findByUserId(Integer id);

    Optional<List<Booking>> findByShowId(Integer id);

    Optional<List<Booking>> findByBookingStatus(BookingStatus bookingStatus);
}