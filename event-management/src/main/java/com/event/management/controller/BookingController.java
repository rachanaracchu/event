package com.event.management.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.event.management.model.Booking;
import com.event.management.repository.BookingRepository;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDate.now());
        }
        return bookingRepository.save(booking);
    }

    @GetMapping
    public java.util.List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
