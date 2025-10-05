package com.vinay.barfa.Movie.Booking.Application.repository;

import com.vinay.barfa.Movie.Booking.Application.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<List<Movie>> findByGenre(String genre);

    Optional<List<Movie>> findByLanguage(String language);


//    @Query(value = "SELECT * From movie where id = :id", nativeQuery = true)
//    Movie findById(MovieDto movieDto, Integer id);

    Optional<Movie> findByName(String title);
}