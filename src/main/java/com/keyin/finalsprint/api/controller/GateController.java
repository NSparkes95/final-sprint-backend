package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // CREATE: accepts {code, airportId} OR {code, airport:{id}}
    @PostMapping(value = "/gate", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGate(@RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String code = body.get("code") == null ? null : String.valueOf(body.get("code"));
        Long airportId = extractAirportId(body);
        if (airportId == null) {
            return ResponseEntity.badRequest().body("airportId (or airport.id) is required");
        }

        Gate g = new Gate();
        g.setCode(code);
        Airport a = new Airport();
        a.setId(airportId);
        g.setAirport(a);

        Gate created = gateService.createGate(g);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // UPDATE: accepts {code, airportId} OR {code, airport:{id}}
    @PutMapping(value = "/gate/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGate(@PathVariable long id, @RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String code = body.get("code") == null ? null : String.valueOf(body.get("code"));
        Long airportId = extractAirportId(body);
        if (airportId == null) {
            return ResponseEntity.badRequest().body("airportId (or airport.id) is required");
        }

        try {
            Gate g = new Gate();
            g.setId(id);
            g.setCode(code);
            Airport a = new Airport();
            a.setId(airportId);
            g.setAirport(a);

            Gate saved = gateService.updateGate(id, g);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/gate/{id}")
    public ResponseEntity<?> deleteGate(@PathVariable long id) {
        try {
            gateService.deleteGate(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException dive) {
            // FK constraint (e.g., flights referencing this gate)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Gate is referenced by flights.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete gate.");
        }
    }

    @GetMapping("/gate/{id}/airport")
    public ResponseEntity<Airport> getAirportByGateId(@PathVariable long id) {
        return gateService.getGateById(id)
                .map(gate -> ResponseEntity.ok(gate.getAirport()))
                .orElse(ResponseEntity.notFound().build());
    }

    // -------- helpers --------
    private Long extractAirportId(Map<String, Object> body) {
        Object airportIdVal = body.get("airportId");
        if (airportIdVal != null) {
            try { return Long.valueOf(String.valueOf(airportIdVal)); } catch (NumberFormatException ignored) {}
        }
        Object airportObj = body.get("airport");
        if (airportObj instanceof Map<?,?> m) {
            Object id = m.get("id");
            if (id != null) {
                try { return Long.valueOf(String.valueOf(id)); } catch (NumberFormatException ignored) {}
            }
        }
        return null;
    }
}
