package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.GateRequest;
import com.keyin.finalsprint.api.dto.GateResponse;
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
    public ResponseEntity<GateResponse> createGate(@Valid @RequestBody GateRequest request) {
        return ResponseEntity.status(201).body(gateService.createGate(request));
    }

    @PutMapping("/gate/{id}")
    public ResponseEntity<GateResponse> updateGate(@PathVariable long id, @Valid @RequestBody GateRequest request) {
        return ResponseEntity.ok(gateService.updateGate(id, request));
    }

    @DeleteMapping("/gate/{id}")
    public ResponseEntity<Void> deleteGate(@PathVariable long id) {
        gateService.deleteGate(id);
        return ResponseEntity.noContent().build();
    }
}
