package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.dto.BookingDTO;
import com.vinay.barfa.Movie.Booking.Application.model.Booking;
import com.vinay.barfa.Movie.Booking.Application.model.Show;
import com.vinay.barfa.Movie.Booking.Application.model.User;
import com.vinay.barfa.Movie.Booking.Application.repository.BookingRepository;
import com.vinay.barfa.Movie.Booking.Application.repository.ShowRepository;
import com.vinay.barfa.Movie.Booking.Application.repository.UserRepository;
import com.vinay.barfa.Movie.Booking.Application.role.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public Booking addBooking(BookingDTO bookingDto) {

        Show show = showRepository.findById(bookingDto.getShowId()).orElseThrow(() -> new RuntimeException("Show not found"));

        // seat available
        if (!isSeatsAvailable(show.getId(), bookingDto.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seat are available");
        }

        // number of seat same
        if (bookingDto.getSeatNumbers().size() != bookingDto.getNumberOfSeats()) {
            throw new RuntimeException("seat number and Number of seat must be equal");
        }

        // seats are not duplicate
        validateDuplicateSeat(show.getId(), bookingDto.getSeatNumbers());

        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User is not found"));

//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        Booking booking = modelMapper.map(bookingDto, Booking.class);
//        return bookingRepository.save(booking);
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDto.getNumberOfSeats()));
        booking.setSeatNumbers(bookingDto.getSeatNumbers());
        booking.setSeatNumbers(bookingDto.getSeatNumbers());
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    private Double calculateTotalAmount(Double price, Integer numberOfSeats) {
        return price * numberOfSeats;
    }

    public void validateDuplicateSeat(int id, List<String> requestedSeatNumbers) {
        Show show = showRepository.findById(id).orElseThrow(() -> new RuntimeException("Show not found"));
        Set<String> occupiedSeat = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(booking -> booking.getSeatNumbers().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats = requestedSeatNumbers
                .stream().filter(occupiedSeat::contains).collect(Collectors.toList());

        if (!duplicateSeats.isEmpty()) {
            throw new RuntimeException("Seats are already booked");
        }
    }

    public boolean isSeatsAvailable(int id, Integer numberOfRequestedSeats) {
        Show show = showRepository.findById(id).orElseThrow(() -> new RuntimeException("Show not found"));
        int bookingSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)   // Here we are filtering(select) those booking which are CONFIM or PADDING
//                .mapToInt(x -> x.getNumberOfSeats())
//                .sum();
                .mapToInt(Booking::getNumberOfSeats)  // here we use method reference on the place on of
                .sum();

        return (show.getTheater().getTheaterCapacity()) >= numberOfRequestedSeats;
    }

    public List<Booking> getAllUserBookings(Integer id) {
        return bookingRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Booking user not found"));
    }

    public List<Booking> getAllShowBookings(Integer id) {
        return bookingRepository.findByShowId(id).orElseThrow(() -> new RuntimeException("Booking show not available"));
    }

    public Booking confirmBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking is not in pending state");
        }

        // PAYMENT PROCESS
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByStatus(BookingStatus bookingStatus) {
        return bookingRepository.findByBookingStatus(bookingStatus).orElseThrow();
    }

    public Booking cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        validateCancellation(booking);

        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    private void validateCancellation(Booking booking) {
        LocalDateTime showTime = booking.getShow().getShowTime();  /// If show time is 6:00 (show-time) so you can't cancel the booking after 4:00(deadline)
        LocalDateTime deadline = showTime.minusHours(2);

        if (LocalDateTime.now().isAfter(deadline)) {
            throw new RuntimeException("you can't cancel the booking");
        }

        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already been cancelled");
        }

    }
}
