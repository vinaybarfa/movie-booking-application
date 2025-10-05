package com.vinay.barfa.Movie.Booking.Application.service;

import com.vinay.barfa.Movie.Booking.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@RequiredArgsConstructor
class AuthenticationServiceTest {
    private final UserRepository userRepository;

    @Test
    void registerNormalUser() {

    }

    @Test
    void registerAdminUser() {
    }


    @Test
    public void findFactorial() {
        int facto = 1;
        for (int i = 5; i >= 1; i--) {
            facto *= i;
        }
        assertEquals(120,facto);
    }

    @Test
    public void testFindByUserName() {
        assertNotNull(userRepository.findById(1));
    }



}