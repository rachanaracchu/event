package com.event.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.management.model.Feedback;
import com.event.management.repository.FeedbackRepository;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:5500") // or your frontend port
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Add feedback
    @PostMapping
    public String addFeedback(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);
        return "Feedback submitted successfully!";
    }

    // Get all feedbacks (optional)
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
