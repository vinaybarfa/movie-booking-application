package com.vinay.barfa.Movie.Booking.Application.controller;

import com.vinay.barfa.Movie.Booking.Application.dto.ShowDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Show;
import com.vinay.barfa.Movie.Booking.Application.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/show")
public class ShowController {

    // createShow , getAllShow, getShowByMovie
    private final ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO show) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.createShow(show));
    }

    @GetMapping("/allShow")
    public ResponseEntity<List<Show>> getAllShow() {
        return ResponseEntity.status(HttpStatus.OK).body(showService.getAllShow());
    }

    @GetMapping("/movieById/{movieId}")
    public ResponseEntity<List<Show>> findByMovieId(@PathVariable Integer movieId) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.getShowByMovie(movieId));
    }

    @GetMapping("/theaterById/{id}")
    public ResponseEntity<List<Show>> findByTheaterId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.getShowByTheaterId(id));
    }

    @PutMapping("/updateShow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Integer id, @RequestBody ShowDTO showDto) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.updateShow(id, showDto));
    }

    @DeleteMapping("/deleteTheater/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Integer id){
        showService.deleteShow(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
