package com.factory.productivity_dashboard.service;

import com.factory.productivity_dashboard.dto.EventRequestDto;
import com.factory.productivity_dashboard.entity.Event;
import com.factory.productivity_dashboard.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepo;

    public EventService(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    public void saveEvent(EventRequestDto dto) {

        Event e = new Event();

        e.setTimestamp(dto.getTimestamp());
        e.setWorkerId(dto.getWorkerId());
        e.setWorkstationId(dto.getWorkstationId());
        e.setEventType(dto.getEventType());
        e.setConfidence(dto.getConfidence());
        e.setCount(dto.getCount());

        eventRepo.save(e);
    }
}
