package com.devart.helloevents.repository;

import com.devart.helloevents.model.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {
}
