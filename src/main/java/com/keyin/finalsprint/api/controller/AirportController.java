package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.AirportRequest;
import com.keyin.finalsprint.api.dto.AirportResponse;
import com.keyin.finalsprint.api.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/airport")
@Tag(name = "Airport API", description = "Operations related to airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    @Operation(summary = "Get all airports", description = "Retrieve a list of all airports")
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get airport by ID", description = "Retrieve an airport by its ID")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable long id) {
        return airportService.getAirportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new airport", description = "Add a new airport to the system")
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest airportRequest) {
        AirportResponse created = airportService.createAirport(airportRequest);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update airport", description = "Update details of an existing airport")
    public ResponseEntity<AirportResponse> updateAirport(
            @PathVariable long id,
            @Valid @RequestBody AirportRequest airportRequest
    ) {
        try {
            AirportResponse updated = airportService.updateAirport(id, airportRequest);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete airport", description = "Remove an airport from the system")
    public ResponseEntity<Void> deleteAirport(@PathVariable long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
