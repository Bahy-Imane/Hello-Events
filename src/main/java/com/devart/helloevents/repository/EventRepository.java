package com.devart.helloevents.repository;

import com.devart.helloevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDateOrLocationOrCategory(LocalDateTime date, String location, String category);
}
