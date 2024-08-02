package com.devart.helloevents.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String image;
    private LocalDateTime date;
    private int capacity;
}