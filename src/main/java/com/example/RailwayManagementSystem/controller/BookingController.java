package com.example.RailwayManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.RailwayManagementSystem.model.Booking;
import com.example.RailwayManagementSystem.service.BookingService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookSeat(@RequestBody Map<String, Long> requestData) {
        Long userId = requestData.get("userId");
        Long trainId = requestData.get("trainId");

        if (userId == null || trainId == null) {
            return ResponseEntity.badRequest().body("Missing userId or trainId");
        }

        String response = bookingService.bookSeat(userId, trainId);

        return response.contains("successfully")
                ? ResponseEntity.ok(Map.of("message", response))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);

        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Booking not found!"));
        }
    }
}