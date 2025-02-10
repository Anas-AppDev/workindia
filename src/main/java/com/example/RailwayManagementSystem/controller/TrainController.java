package com.example.RailwayManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.RailwayManagementSystem.model.Train;
import com.example.RailwayManagementSystem.service.TrainService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/train")
public class TrainController {
    private static final String ADMIN_API_KEY = "MyApi"; // Change this to a secure value

    @Autowired
    private TrainService trainService;

    private boolean isValidAdminKey(String apiKey) {
        return ADMIN_API_KEY.equals(apiKey);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTrain(@RequestHeader(value = "X-API-KEY", required = false) String apiKey, 
                                      @RequestBody Train train) {
        if (!isValidAdminKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.ok(trainService.addTrain(train));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTrains(@RequestParam String source, @RequestParam String destination) {
        List<Train> trains = trainService.getTrains(source, destination);

        if (trains.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Train Available");
        }

        return ResponseEntity.ok(trains);
    }
}