package com.devart.helloevents.controller;

import com.devart.helloevents.dto.BookingDTO;
import com.devart.helloevents.model.Booking;
import com.devart.helloevents.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reserve")
    public String bookEvent(@RequestBody BookingDTO bookingRequest, Authentication authentication) {
        return bookingService.reserveTickets(bookingRequest, authentication);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all/bookings")
    public List<Booking> getAllBookingsByUser(Authentication authentication) {
        return bookingService.getAllBookingsByUser(authentication);
    }
}
