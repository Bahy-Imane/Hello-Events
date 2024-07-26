package com.devart.helloevents.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime date;
    private int capacity;
}
