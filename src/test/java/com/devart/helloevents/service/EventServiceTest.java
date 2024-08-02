//package com.devart.helloevents.service;
//
//import com.devart.helloevents.dto.EventDTO;
//import com.devart.helloevents.model.Event;
//import com.devart.helloevents.repository.EventRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EventServiceTest {
//
//    @Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventService eventService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllEvents() {
//        Event event1 = new Event(1L, "Event 1", "Description 1", "Location 1", LocalDateTime.now(), 100, null);
//        Event event2 = new Event(2L, "Event 2", "Description 2", "Location 2", LocalDateTime.now().plusDays(1), 200, null);
//        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));
//
//        List<EventDTO> result = eventService.getAllEvents();
//
//        assertEquals(2, result.size());
//        assertEquals("Event 1", result.get(0).getName());
//        assertEquals("Event 2", result.get(1).getName());
//    }
//
//    @Test
//    void createEvent() {
//        EventDTO eventDTO = new EventDTO();
//        eventDTO.setName("New Event");
//        eventDTO.setDescription("New Description");
//        eventDTO.setLocation("New Location");
//        eventDTO.setDate(LocalDateTime.now());
//        eventDTO.setCapacity(50);
//
//        Event savedEvent = new Event(1L, "New Event", "New Description", "New Location", LocalDateTime.now(), 50, null);
//        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);
//
//        EventDTO result = eventService.createEvent(eventDTO);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("New Event", result.getName());
//    }
//
//    @Test
//    void updateEvent() {
//        Long eventId = 1L;
//        EventDTO eventDTO = new EventDTO();
//        eventDTO.setName("Updated Event");
//        eventDTO.setDescription("Updated Description");
//        eventDTO.setLocation("Updated Location");
//        eventDTO.setDate(LocalDateTime.now());
//        eventDTO.setCapacity(75);
//
//        Event existingEvent = new Event(eventId, "Old Event", "Old Description", "Old Location", LocalDateTime.now().minusDays(1), 100, null);
//        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
//        when(eventRepository.save(any(Event.class))).thenReturn(existingEvent);
//
//        EventDTO result = eventService.updateEvent(eventId, eventDTO);
//
//        assertNotNull(result);
//        assertEquals(eventId, result.getId());
//        assertEquals("Updated Event", result.getName());
//        assertEquals("Updated Description", result.getDescription());
//        assertEquals(75, result.getCapacity());
//    }
//
//    @Test
//    void deleteEvent() {
//        Long eventId = 1L;
//        eventService.deleteEvent(eventId);
//        verify(eventRepository, times(1)).deleteById(eventId);
//    }
//
//    @Test
//    void searchEvents() {
//        LocalDateTime searchDate = LocalDateTime.now();
//        String searchLocation = "Search Location";
//
//        Event event1 = new Event(1L, "Event 1", "Description 1", searchLocation, searchDate, 100, null);
//        Event event2 = new Event(2L, "Event 2", "Description 2", "Other Location", searchDate.plusDays(1), 200, null);
//
//        when(eventRepository.findByDateOrLocation(searchDate, searchLocation))
//                .thenReturn(Arrays.asList(event1, event2));
//
//        List<EventDTO> result = eventService.searchEvents(searchDate, searchLocation);
//
//        assertEquals(2, result.size());
//        assertEquals("Event 1", result.get(0).getName());
//        assertEquals("Event 2", result.get(1).getName());
//    }
//}