//package com.devart.helloevents.service;
//
//import com.devart.helloevents.model.Event;
//import com.devart.helloevents.model.TicketReservation;
//import com.devart.helloevents.repository.EventRepository;
//import com.devart.helloevents.repository.TicketReservationRepository;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class TicketReservationService {
//
//    @Autowired
//    private TicketReservationRepository ticketReservationRepository;
//    private EventRepository eventRepository;
//    public String reserveTickets(Long eventId, String userName, int numberOfTickets) {
//        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
//
//        if (event.getAvailableTickets() < numberOfTickets) {
//            return "Not enough tickets available";
//        }
//
//        event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
//        eventRepository.save(event);
//
//        TicketReservation reservation = new TicketReservation();
//        reservation.setUserName(userName);
//        reservation.setReservationDate(LocalDateTime.now());
//        reservation.setNumberOfTickets(numberOfTickets);
//        reservation.setEvent(event);
//
//        ticketReservationRepository.save(reservation);
//
//        return "Reservation successful";
//    }
//
//}
