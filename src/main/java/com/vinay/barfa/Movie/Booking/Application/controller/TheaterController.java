package com.vinay.barfa.Movie.Booking.Application.controller;

import com.vinay.barfa.Movie.Booking.Application.dto.TheaterDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Theater;
import com.vinay.barfa.Movie.Booking.Application.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/theater")
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping("/add-theater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDto) {
        return ResponseEntity.ok().body(theaterService.addTheater(theaterDto));
    }


    @GetMapping("/api/theaterbylocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location) {
        return ResponseEntity.ok().body(theaterService.getTheaterByLocation(location));
    }

    @PutMapping("/updatetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Integer id, @RequestBody TheaterDTO theaterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(theaterService.updateTheater(id, theaterDto));
    }

    @DeleteMapping("/deletetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Integer id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
