package com.factory.productivity_dashboard.controller;

import com.factory.productivity_dashboard.dto.EventRequestDto;
import com.factory.productivity_dashboard.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public String createEvent(@RequestBody EventRequestDto dto) {
        eventService.saveEvent(dto);
        return "Event saved";
    }
}
