package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.dto.TheaterDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Theater;
import com.vinay.barfa.Movie.Booking.Application.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final ModelMapper modelMapper;

    public Theater addTheater(TheaterDTO theaterDto) {
        Theater theater = this.modelMapper.map(theaterDto, Theater.class);
        return theaterRepository.save(theater);
    }


    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> listOfTheaterBox = theaterRepository.findByTheaterLocation(location);

        if (listOfTheaterBox.isPresent()) {
            return listOfTheaterBox.get();
        } else {
            throw new IllegalArgumentException("Something want wrong");
        }
    }

    public Theater updateTheater(Integer id, TheaterDTO theaterDto) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No theater found for id : " + id));

        this.modelMapper.map(theaterDto, theater);
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Integer id) {
        theaterRepository.deleteById(id);
    }
}
