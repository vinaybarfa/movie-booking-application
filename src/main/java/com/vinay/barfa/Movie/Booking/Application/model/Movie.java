package com.vinay.barfa.Movie.Booking.Application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Column(nullable = false)
    private String name;
    //    @Column(nullable = false)
    private String description;
    private String genre;   // category
    private String language;
    private Integer duration;
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    // lazy means, when Move class load the Show class will not load
    @JsonIgnore
    private List<Show> shows = new ArrayList<>();


}
