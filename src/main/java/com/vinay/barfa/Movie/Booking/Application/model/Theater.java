package com.vinay.barfa.Movie.Booking.Application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinay.barfa.Movie.Booking.Application.role.ScreenType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(nullable = false)
    private String theaterName;
//    @Column(nullable = false)
    private String theaterLocation;
    private Integer theaterCapacity;
    @Enumerated(EnumType.STRING)
    private ScreenType theaterScreenType;
    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Show> shows = new HashSet<>();

}
