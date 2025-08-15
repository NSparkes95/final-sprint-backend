package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.AirlineRequest;
import com.keyin.finalsprint.api.dto.AirlineResponse;
import com.keyin.finalsprint.api.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/airlines")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<AirlineResponse>> getAllAirlines() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable long id) {
        return airlineService.getAirlineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(@Valid @RequestBody AirlineRequest request) {
        AirlineResponse created = airlineService.createAirline(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponse> updateAirline(
            @PathVariable long id,
            @Valid @RequestBody AirlineRequest request) {
        try {
            AirlineResponse updated = airlineService.updateAirline(id, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable long id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }
}

