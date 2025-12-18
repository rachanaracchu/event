package com.event.management.repository;

import com.event.management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Use the actual field name in Event entity (here it's "type")
    List<Event> findByType(String type);
}
