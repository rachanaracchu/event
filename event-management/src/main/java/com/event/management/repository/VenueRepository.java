package com.event.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.management.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

    // Must match entity field name: location
    List<Venue> findByLocation(String location);
}
