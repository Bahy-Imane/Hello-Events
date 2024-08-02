package com.devart.helloevents.service;

import com.devart.helloevents.dto.EventDTO;
import com.devart.helloevents.model.Event;
import com.devart.helloevents.repository.EventRepository;
import org.springframework.stereotype.Service;

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

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return convertToDto(event);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return convertToDto(savedEvent);
    }

    public EventDTO updateEvent(Long eventId, EventDTO eventDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        updateEventFields(event, eventDTO);
        Event updatedEvent = eventRepository.save(event);
        return convertToDto(updatedEvent);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<EventDTO> searchEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EventDTO> searchEventsByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EventDTO convertToDto(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setImage(event.getImage());
        eventDTO.setDate(event.getDate());
        eventDTO.setCapacity(event.getCapacity());
        return eventDTO;
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        updateEventFields(event, eventDTO);
        return event;
    }

    private void updateEventFields(Event event, EventDTO eventDTO) {
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setImage(eventDTO.getImage());
        event.setDate(eventDTO.getDate());
        event.setCapacity(eventDTO.getCapacity());
    }
}