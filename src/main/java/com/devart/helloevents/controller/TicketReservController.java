package com.devart.helloevents.controller;


import com.devart.helloevents.service.TicketReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TicketReservController {

    @Autowired
    private TicketReservationService reservationService;


    @PostMapping("/{eventId}/reserve")
    public String reserveTickets(@PathVariable Long eventId, Authentication authentication, @RequestParam int numberOfTickets) {
        return reservationService.reserveTickets(eventId, authentication , numberOfTickets);
    }
}
