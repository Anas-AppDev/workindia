package com.example.RailwayManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.RailwayManagementSystem.model.Train;
import com.example.RailwayManagementSystem.Repository.TrainRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}