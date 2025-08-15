package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.GateResponse;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GateController {
    @Autowired
    private GateService gateService;

    @GetMapping("/gates")
    public ResponseEntity<List<GateResponse>> getAllGates() {
        return ResponseEntity.ok(gateService.getAllGates());
    }

    @GetMapping("/gate/{id}")
    public ResponseEntity<GateResponse> getGateById(@PathVariable long id) {
        return gateService.getGateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/gate")
    public ResponseEntity<Gate> createGate(@RequestBody Gate newGate) {
        Gate created = gateService.createGate(newGate);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/gate/{id}")
    public ResponseEntity<GateResponse> updateGate(@PathVariable long id, @RequestBody Gate updatedGate) {
        try {
            GateResponse gate = gateService.updateGate(id, updatedGate);
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
