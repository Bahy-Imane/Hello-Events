package com.devart.helloevents.repository;

import com.devart.helloevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
