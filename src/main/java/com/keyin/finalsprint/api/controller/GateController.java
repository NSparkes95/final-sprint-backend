package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GateController {

    @Autowired
    private GateService gateService;

    @GetMapping("/gates")
    public ResponseEntity<List<Gate>> getAllGates() {
        return ResponseEntity.ok(gateService.getAllGates());
    }

    @GetMapping("/gate/{id}")
    public ResponseEntity<Gate> getGateById(@PathVariable long id) {
        return gateService.getGateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/gate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGate(@RequestBody Gate newGate) {
        if (newGate.getAirport() == null || newGate.getAirport().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("airport.id is required when creating a gate");
        }
        Gate created = gateService.createGate(newGate);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/gate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGate(@PathVariable long id, @RequestBody Gate updatedGate) {
        if (updatedGate.getAirport() == null || updatedGate.getAirport().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("airport.id is required when updating a gate");
        }
        try {
            Gate gate = gateService.updateGate(id, updatedGate);
            return ResponseEntity.ok(gate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/gate/{id}")
    public ResponseEntity<Void> deleteGate(@PathVariable long id) {
        gateService.deleteGate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gate/{id}/airport")
    public ResponseEntity<Airport> getAirportByGateId(@PathVariable long id) {
        return gateService.getGateById(id)
                .map(gate -> ResponseEntity.ok(gate.getAirport()))
                .orElse(ResponseEntity.notFound().build());
    }
}
