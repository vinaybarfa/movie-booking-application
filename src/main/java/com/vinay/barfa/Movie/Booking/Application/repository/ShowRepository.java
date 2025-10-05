package com.vinay.barfa.Movie.Booking.Application.repository;

import com.vinay.barfa.Movie.Booking.Application.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    Optional<List<Show>> findByMovieId(Integer movieId);

    Optional<List<Show>> findByTheaterId(Integer theaterId);
}