package com.event.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.event.management.model.Venue;
import com.event.management.repository.VenueRepository;

@RestController
@RequestMapping("/api/venue")
@CrossOrigin(origins = "*")
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable Long id) {
        return venueRepository.findById(id).orElse(null);
    }
}
