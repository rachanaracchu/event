package com.event.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.management.model.Feedback;
import com.event.management.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    public List<Feedback> getAllFeedback() {
        return repository.findAll();
    }

    public Feedback addFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        repository.deleteById(id);
    }
}
