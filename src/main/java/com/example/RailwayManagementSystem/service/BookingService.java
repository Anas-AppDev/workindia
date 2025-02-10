package com.example.RailwayManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.RailwayManagementSystem.model.Booking;
import com.example.RailwayManagementSystem.model.Train;
import com.example.RailwayManagementSystem.model.User;
import com.example.RailwayManagementSystem.Repository.BookingRepository;
import com.example.RailwayManagementSystem.Repository.TrainRepository;
import com.example.RailwayManagementSystem.Repository.UserRepository;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository; // Add User Repository

    @Transactional // Ensure atomic operations
    public synchronized String bookSeat(Long userId, Long trainId) {
        Optional<Train> optionalTrain = trainRepository.findById(trainId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalTrain.isEmpty()) {
            return "Train not found";
        }
        
        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        Train train = optionalTrain.get();
        User user = optionalUser.get();

        // Allow all users to book if seats are available
        if (train.getAvailableSeats() <= 0) {
            return "No seats available for booking";
        }

        // Decrease seat count
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        // Create and save booking
        Booking booking = new Booking();
        booking.setTrain(train);
        booking.setUser(user);
        booking.setPassengerName(user.getUsername()); // Use username as passenger name
        bookingRepository.save(booking);

        return "Seat booked successfully! Your Booking ID: " + booking.getId();
    }


    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }
}