package com.event.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.management.model.Event;
import com.event.management.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    // Get all events
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    // Get events by type (case-insensitive)
    public List<Event> getEventsByType(String type) {
        return repository.findByTypeIgnoreCase(type);
    }

    // Add a new event
    public Event addEvent(Event event) {
        return repository.save(event);
    }

    // Update existing event
    public Event updateEvent(Long id, Event event) {
        event.setId(id);
        return repository.save(event);
    }

    // Delete an event
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}
