package com.example.RailwayManagementSystem.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.example.RailwayManagementSystem.model.Train;

import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findBySourceAndDestination(String source, String destination);

    @Lock(LockModeType.PESSIMISTIC_WRITE)  // Lock row during update
    Optional<Train> findById(Long id);
}


