package com.event.management.controller;

import com.event.management.model.Event;
import com.event.management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted with id: " + id;
    }

    @GetMapping("/type/{type}")
    public List<Event> getEventsByType(@PathVariable String type) {
        return eventService.getEventsByType(type); // fixed endpoint
    }
}
