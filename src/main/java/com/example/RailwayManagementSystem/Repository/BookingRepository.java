package com.example.RailwayManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.RailwayManagementSystem.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
