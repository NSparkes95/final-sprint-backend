package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.PassengerRequest;
import com.keyin.finalsprint.api.dto.PassengerResponse;
import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Passenger;
import com.keyin.finalsprint.api.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public ResponseEntity<List<PassengerResponse>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/passenger/{id}")
    public ResponseEntity<PassengerResponse> getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/passenger")
    public ResponseEntity<PassengerResponse> createPassenger(@Valid @RequestBody PassengerRequest request) {
        PassengerResponse created = passengerService.createPassenger(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/passenger/{id}")
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

    @DeleteMapping("/passenger/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/passenger/{id}/aircraft")
    public ResponseEntity<Object> getAircraftByPassengerId(@PathVariable long id) {
        return passengerService.getPassengerAircraftNames(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/passenger/{id}/airports")
    public ResponseEntity<List<String>> getAirportsUsedByPassenger(@PathVariable long id) {
        return passengerService.getPassengerAirportNames(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}