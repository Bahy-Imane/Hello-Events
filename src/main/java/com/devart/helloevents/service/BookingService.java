package com.devart.helloevents.service;

import com.devart.helloevents.dto.BookingDTO;
import com.devart.helloevents.model.Booking;
import com.devart.helloevents.model.Event;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.BookingRepository;
import com.devart.helloevents.repository.EventRepository;
import com.devart.helloevents.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public String reserveTickets(BookingDTO bookingDTO, Authentication authentication) {
        User user = userRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(bookingDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getCapacity() < bookingDTO.getNumberOfTickets()) {
            return "Not enough tickets available";
        }

        event.setCapacity(event.getCapacity() - bookingDTO.getNumberOfTickets());
        eventRepository.save(event);

        Booking reservation = new Booking();
        reservation.setUser(user);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setNumberOfTickets(bookingDTO.getNumberOfTickets());
        reservation.setEvent(event);

        bookingRepository.save(reservation);

        return "Reservation successful";
    }

    public List<Booking> getAllBookingsByUser(Authentication authentication) {
        User user = userRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }
}
