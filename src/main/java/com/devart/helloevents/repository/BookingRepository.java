package com.devart.helloevents.repository;

import com.devart.helloevents.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
