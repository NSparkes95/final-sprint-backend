package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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

    // CREATE: tolerant JSON (kills 415), validates name
    @PostMapping(value = "/airport", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAirport(@RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String name = body.get("name") == null ? null : String.valueOf(body.get("name")).trim();
        String code = body.get("code") == null ? null : String.valueOf(body.get("code")).trim();

        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body("name is required");
        }

        Airport a = new Airport();
        a.setName(name);
        if (code != null && !code.isBlank()) a.setCode(code);

        Airport created = airportService.createAirport(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // UPDATE: tolerant JSON (kills 415), validates name
    @PutMapping(value = "/airport/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAirport(@PathVariable long id, @RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String name = body.get("name") == null ? null : String.valueOf(body.get("name")).trim();
        String code = body.get("code") == null ? null : String.valueOf(body.get("code")).trim();

        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body("name is required");
        }

        try {
            Airport a = new Airport();
            a.setId(id);
            a.setName(name);
            if (code != null && !code.isBlank()) a.setCode(code);

            Airport saved = airportService.updateAirport(id, a);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/airport/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable long id) {
        try {
            airportService.deleteAirport(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException dive) {
            // FK constraint (gates/flights still reference this airport)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Airport is referenced by gates or flights.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete airport.");
        }
    }
}
