package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.PassengerRequest;
import com.keyin.finalsprint.api.dto.PassengerResponse;
import com.keyin.finalsprint.api.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/passengers")
@Tag(name = "Passenger API", description = "Operations related to passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Operation(summary = "Get all passengers", description = "Retrieve a list of all passengers.")
    @ApiResponse(responseCode = "200", description = "List of passengers",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PassengerResponse.class)))
    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @Operation(summary = "Get passenger by ID", description = "Retrieve a passenger by their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Passenger found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PassengerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Passenger not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponse> getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a passenger", description = "Add a new passenger.")
    @ApiResponse(responseCode = "201", description = "Passenger created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PassengerResponse.class)))
    @PostMapping
    public ResponseEntity<PassengerResponse> createPassenger(@Valid @RequestBody PassengerRequest request) {
        PassengerResponse created = passengerService.createPassenger(request);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Update a passenger", description = "Modify an existing passenger's details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Passenger updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PassengerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Passenger not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PassengerResponse> updatePassenger(
            @PathVariable long id,
            @Valid @RequestBody PassengerRequest request
    ) {
        try {
            PassengerResponse updated = passengerService.updatePassenger(id, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a passenger", description = "Remove a passenger by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Passenger deleted"),
            @ApiResponse(responseCode = "404", description = "Passenger not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get aircraft used by passenger", description = "Retrieve all aircraft names associated with a passenger.")
    @ApiResponse(responseCode = "200", description = "Aircraft list retrieved")
    @GetMapping("/{id}/aircraft")
    public ResponseEntity<Object> getAircraftByPassengerId(@PathVariable long id) {
        return passengerService.getPassengerAircraftNames(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get airports used by passenger", description = "Retrieve all airport names used by a passenger.")
    @ApiResponse(responseCode = "200", description = "Airport list retrieved")
    @GetMapping("/{id}/airports")
    public ResponseEntity<List<String>> getAirportsUsedByPassenger(@PathVariable long id) {
        return passengerService.getPassengerAirportNames(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
