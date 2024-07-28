package com.devart.helloevents.service;

import com.devart.helloevents.dto.BookingDTO;
import com.devart.helloevents.model.Booking;
import com.devart.helloevents.model.Event;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.BookingRepository;
import com.devart.helloevents.repository.EventRepository;
import com.devart.helloevents.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reserveTickets_Success() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setEventId(1L);
        bookingDTO.setNumberOfTickets(2);

        User user = new User();
        user.setUserName("testUser");

        Event event = new Event();
        event.setId(1L);
        event.setCapacity(5);

        when(authentication.getName()).thenReturn("testUser");
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        String result = bookingService.reserveTickets(bookingDTO, authentication);

        assertEquals("Reservation successful", result);
        assertEquals(3, event.getCapacity());
        verify(eventRepository, times(1)).save(event);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void reserveTickets_NotEnoughTickets() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setEventId(1L);
        bookingDTO.setNumberOfTickets(6);

        User user = new User();
        user.setUserName("testUser");

        Event event = new Event();
        event.setId(1L);
        event.setCapacity(5);

        when(authentication.getName()).thenReturn("testUser");
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        String result = bookingService.reserveTickets(bookingDTO, authentication);

        assertEquals("Not enough tickets available", result);
        assertEquals(5, event.getCapacity());
        verify(eventRepository, never()).save(event);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    void getAllBookingsByUser() {
        User user = new User();
        user.setUserName("testUser");

        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(authentication.getName()).thenReturn("testUser");
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookingsByUser(authentication);

        assertEquals(2, result.size());
        verify(bookingRepository, times(1)).findByUser(user);
    }
}