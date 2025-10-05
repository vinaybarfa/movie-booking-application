package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.dto.MovieDto;
import com.vinay.barfa.Movie.Booking.Application.model.Movie;
import com.vinay.barfa.Movie.Booking.Application.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
//        return movieRepository.findAll().stream().map(movie -> modelMapper.map(movie, MovieDto.class)).collect(Collectors.toList());
        return movieRepository.findAll();
    }

    public Movie addMovie(MovieDto movieDto) {
//        return movieRepository.save(movieDto);
//        Movie movie = Movie.builder()
//                .name(movieDto.getName())
//                .description(movieDto.getDescription())
//                .language(movieDto.getLanguage())
//                .duration(movieDto.getDuration())
//                .releaseDate(movieDto.getReleaseDate())
//                .genre(movieDto.getGenre())
//                .build();

//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Movie movie1 = this.modelMapper.map(movieDto, Movie.class);

        return movieRepository.save(movie1);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        Optional<List<Movie>> listOfMovieBox = movieRepository.findByGenre(genre);
        if (listOfMovieBox.isPresent()) {
            return listOfMovieBox.get();
        }
        throw new RuntimeException("No movies found for genre " + genre);
    }

    public List<Movie> getMoviesByLanguage(String language) {
        Optional<List<Movie>> listOfMovieBox = movieRepository.findByLanguage(language);
        if (listOfMovieBox.isPresent()) {
            return listOfMovieBox.get();
        }
        throw new RuntimeException("No movies found for language " + language);
    }

    public Movie getMoviesByTitle(String title) {
        Optional<Movie> movieBox = movieRepository.findByName(title);
        if (movieBox.isPresent()) {
            return movieBox.get();
        }
        throw new RuntimeException("No movies found for title " + title);
    }

    public Movie updateMovie(Integer id, MovieDto movieDto) {

        // 1. Fetch the existing entity, throwing 404 if not found
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Movie found for the id: " + id));

        // 2. Map DTO properties onto the EXISTING entity. This preserves the ID.
        this.modelMapper.map(movieDto, existingMovie);

        // 3. Save the updated existing entity
        return movieRepository.save(existingMovie);
    }
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}


