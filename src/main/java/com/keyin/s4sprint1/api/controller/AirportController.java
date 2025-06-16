package com.keyin.s4sprint1.api.controller;

import com.keyin.s4sprint1.api.entity.Airport;
import com.keyin.s4sprint1.api.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable long id) {
        return airportService.getAirportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/airport")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport newAirport) {
        Airport created = airportService.createAirport(newAirport);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/airport/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable long id, @RequestBody Airport updatedAirport) {
        try {
            Airport airport = airportService.updateAirport(id, updatedAirport);
            return ResponseEntity.ok(airport);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/airport/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}

