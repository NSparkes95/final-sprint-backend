package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    public ResponseEntity<List<Aircraft>> getAllAircrafts() {
        return ResponseEntity.ok(aircraftService.getAllAircraft());
    }

    // GET /aircraft works (prevents 405 error)
    @GetMapping("/aircraft")
    public ResponseEntity<List<Aircraft>> getAllAircraftsAlias() {
        return ResponseEntity.ok(aircraftService.getAllAircraft());
    }

    @GetMapping("/aircraft/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable long id) {
        return aircraftService.getAircraftById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/aircraft")
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft newAircraft) {
        Aircraft created = aircraftService.createAircraft(newAircraft);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/aircraft/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        try {
            Aircraft aircraft = aircraftService.updateAircraft(id, updatedAircraft);
            return ResponseEntity.ok(aircraft);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/aircraft/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aircraft/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsByAircraftId(@PathVariable long id) {
        return aircraftService.getAircraftById(id)
                .map(aircraft -> ResponseEntity.ok(aircraft.getAirports()))
                .orElse(ResponseEntity.notFound().build());
    }
}
