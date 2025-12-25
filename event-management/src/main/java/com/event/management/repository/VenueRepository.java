package com.event.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.management.model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
