//package com.devart.helloevents.controller;
//
//
//import com.devart.helloevents.model.TicketReservation;
//import com.devart.helloevents.service.TicketReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping
//public class TicketReservController {
//
//    private TicketReservationService ticketReservationService;
//    private
//
//    @PostMapping("/{eventId}/reserve")
//    public String reserveTickets(@PathVariable Long eventId, @RequestParam String userName, @RequestParam int numberOfTickets) {
//        return ticketReservationService.reserveTickets(eventId, userName, numberOfTickets);
//    }
//}
