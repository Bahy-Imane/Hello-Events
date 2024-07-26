package com.devart.helloevents.service;

import com.devart.helloevents.model.Event;
import com.devart.helloevents.model.TicketReservation;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.EventRepository;
import com.devart.helloevents.repository.TicketReservationRepository;
import com.devart.helloevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketReservationService {

    @Autowired
    private TicketReservationRepository ticketReservationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public String reserveTickets(Long eventId, Authentication authentication, int numberOfTickets) {
        User user =userRepository.findByUserName(authentication.getName()).orElseThrow(()->new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getCapacity() < numberOfTickets) {
            return "Not enough tickets available";
        }

        event.setCapacity(event.getCapacity() - numberOfTickets);
        eventRepository.save(event);

        TicketReservation reservation = new TicketReservation();
        reservation.setUser(user);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setNumberOfTickets(numberOfTickets);
        reservation.setEvent(event);

        ticketReservationRepository.save(reservation);

        return "Reservation successful";
    }

}
