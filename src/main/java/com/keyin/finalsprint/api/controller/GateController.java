package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.GateRequest;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.GateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GateController {
    @Autowired
    private GateService gateService;

    @PostMapping("/gate")
    public ResponseEntity<Gate> createGate(@Valid @RequestBody GateRequest gateRequest) {
        Gate created = gateService.createGate(gateRequest);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/gate/{id}")
    public ResponseEntity<Gate> getGateById(@PathVariable long id) {
        return gateService.getGateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/gate/{id}")
    public ResponseEntity<Gate> updateGate(@PathVariable long id, @Valid @RequestBody Gate updatedGate) {
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
