package com.devart.helloevents.service;

import com.devart.helloevents.dto.EventDTO;
import com.devart.helloevents.model.Event;
import com.devart.helloevents.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        return convertToDto(eventRepository.save(event));
    }

    public EventDTO updateEvent(Long eventId, EventDTO eventDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());
        event.setCapacity(eventDTO.getCapacity());
        return convertToDto(eventRepository.save(event));
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<EventDTO> searchEvents(LocalDateTime date, String location, String category) {
        return eventRepository.findByDateOrLocationOrCategory(date, location, category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EventDTO convertToDto(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate(event.getDate());
        eventDTO.setCapacity(event.getCapacity());
        return eventDTO;
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());
        event.setCapacity(eventDTO.getCapacity());
        return event;
    }
}
