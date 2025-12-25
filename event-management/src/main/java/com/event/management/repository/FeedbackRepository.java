package com.event.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.management.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
