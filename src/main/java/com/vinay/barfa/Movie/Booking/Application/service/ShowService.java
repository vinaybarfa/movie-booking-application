package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.dto.ShowDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Booking;
import com.vinay.barfa.Movie.Booking.Application.model.Movie;
import com.vinay.barfa.Movie.Booking.Application.model.Show;
import com.vinay.barfa.Movie.Booking.Application.model.Theater;
import com.vinay.barfa.Movie.Booking.Application.repository.MovieRepository;
import com.vinay.barfa.Movie.Booking.Application.repository.ShowRepository;
import com.vinay.barfa.Movie.Booking.Application.repository.TheaterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService {


    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ModelMapper modelMapper;

    public Show createShow(ShowDTO showDto) {

        Movie movie = movieRepository.findById(showDto.getMovieId()).orElseThrow(() -> new EntityNotFoundException("No Movie found for id : " + showDto.getMovieId()));
        Theater theater = theaterRepository.findById(showDto.getTheaterId()).orElseThrow(() -> new EntityNotFoundException("No Theater found for id : " + showDto.getTheaterId()));
//
        Show show = new Show();
        show.setShowTime(showDto.getShowTime());
        show.setPrice(showDto.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
        return showRepository.save(show);
    }

    public List<Show> getAllShow() {
        return showRepository.findAll();
    }

    public List<Show> getShowByMovie(Integer movieId) {
        Optional<List<Show>> showListBox = showRepository.findByMovieId(movieId);
        if (showListBox.isPresent()) {
            return showListBox.get();
        } else {
            throw new IllegalArgumentException("No Movie is found with id : " + movieId);
        }
    }

    public List<Show> getShowByTheaterId(Integer theaterId) {
        Optional<List<Show>> showListBox = showRepository.findByTheaterId(theaterId);
        if (showListBox.isPresent()) {
            return showListBox.get();
        } else {
            throw new IllegalArgumentException("No Movie is found with id : " + theaterId);
        }
    }

    public void deleteShow(Integer id) {
        if (!showRepository.existsById(id)) {
            throw new EntityNotFoundException("No show available for id : " + id);
        }
        List<Booking> bookings = showRepository.findById(id).get().getBookings();
        if (!bookings.isEmpty()) {  // if booking is not empty
            throw new IllegalArgumentException("Can't delete show with existing booking");
        }
        showRepository.deleteById(id);
    }

    public Show updateShow(Integer id, ShowDTO showDto) {
        Movie movie = movieRepository.findById(showDto.getMovieId()).orElseThrow(() -> new EntityNotFoundException("No Movie found for id : " + showDto.getMovieId()));
        Theater theater = theaterRepository.findById(showDto.getTheaterId()).orElseThrow(() -> new EntityNotFoundException("No Theater found for id : " + showDto.getTheaterId()));
        Show show = showRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Show not found"));
        show.setShowTime(showDto.getShowTime());
        show.setPrice(showDto.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
        return showRepository.save(show);
    }
}
