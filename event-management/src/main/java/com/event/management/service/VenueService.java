package com.event.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.management.model.Venue;
import com.event.management.repository.VenueRepository;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    public Venue addVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public List<Venue> getVenuesByLocation(String location) {
        return venueRepository.findByLocation(location);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }

    public Venue updateVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}
