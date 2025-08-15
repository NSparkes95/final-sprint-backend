package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Flight;
import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create: tolerant to nested/flat shapes
    @PostMapping(value = "/flight", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFlight(@RequestBody Map<String, Object> body) {
        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String airlineName;
        String type;

        Object ac = body.get("aircraft");
        if (ac instanceof Map<?,?> m) {
            airlineName = str(m.get("airlineName"));
            type        = str(m.get("type"));
        } else {
            airlineName = str(body.get("airlineName"));
            type        = str(body.get("type"));
        }

        Long depId  = extractId(body, "departureAirportId", "departureAirport");
        Long arrId  = extractId(body, "arrivalAirportId",   "arrivalAirport");
        Long gateId = extractId(body, "gateId",             "gate");

        if (isBlank(airlineName) || isBlank(type)) {
            return ResponseEntity.badRequest().body("aircraft.airlineName and aircraft.type are required");
        }
        if (depId == null || arrId == null) {
            return ResponseEntity.badRequest().body("departureAirport and arrivalAirport are required");
        }

        Flight f = new Flight();

        Aircraft aircraft = new Aircraft();
        aircraft.setAirlineName(airlineName);
        aircraft.setType(type);
        f.setAircraft(aircraft);

        Airport dep = new Airport(); dep.setId(depId); f.setDepartureAirport(dep);
        Airport arr = new Airport(); arr.setId(arrId); f.setArrivalAirport(arr);

        if (gateId != null) {
            Gate g = new Gate(); g.setId(gateId); f.setGate(g);
        } else {
            f.setGate(null);
        }

        Flight created = flightService.createFlight(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/flight/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFlight(@PathVariable long id, @RequestBody Map<String, Object> body) {
        System.out.println("PUT /flight/" + id + " body=" + body); 

        if (body == null) return ResponseEntity.badRequest().body("Body is required");

        String airlineName;
        String type;

        Object ac = body.get("aircraft");
        if (ac instanceof Map<?,?> m) {
            airlineName = str(m.get("airlineName"));
            type        = str(m.get("type"));
        } else {
            airlineName = str(body.get("airlineName"));
            type        = str(body.get("type"));
        }

        String flightNumber = str(body.get("flightNumber"));

        Long depId      = extractId(body, "departureAirportId", "departureAirport");
        Long arrId      = extractId(body, "arrivalAirportId",   "arrivalAirport");
        Long gateId     = extractId(body, "gateId",             "gate");   
        Long aircraftId = extractId(body, "aircraftId",         "aircraft");  

        if (isBlank(airlineName) || isBlank(type)) {
            return ResponseEntity.badRequest().body("aircraft.airlineName and aircraft.type are required");
        }
        if (depId == null || arrId == null) {
            return ResponseEntity.badRequest().body("departureAirport and arrivalAirport are required");
        }

        try {
            // If client didn't send aircraft.id, reuse current one from DB to avoid transient errors
            if (aircraftId == null) {
                Flight current = flightService.getFlightById(id)
                        .orElseThrow(NoSuchElementException::new);
                if (current.getAircraft() != null) {
                    aircraftId = current.getAircraft().getId();
                }
            }

            Flight f = new Flight();
            f.setId(id);
            if (!isBlank(flightNumber)) {
                f.setFlightNumber(flightNumber);
            }

            Aircraft aircraft = new Aircraft();
            if (aircraftId != null) aircraft.setId(aircraftId);
            aircraft.setAirlineName(airlineName);
            aircraft.setType(type);
            f.setAircraft(aircraft);

            Airport dep = new Airport(); dep.setId(depId); f.setDepartureAirport(dep);
            Airport arr = new Airport(); arr.setId(arrId); f.setArrivalAirport(arr);

            if (gateId != null) {
                Gate g = new Gate(); g.setId(gateId); f.setGate(g);
            } else {
                f.setGate(null);
            }

            Flight saved = flightService.updateFlight(id, f);
            return ResponseEntity.ok(saved);

        } catch (NoSuchElementException nf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found: " + id);

        } catch (IllegalArgumentException bad) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bad.getMessage());

        } catch (DataIntegrityViolationException conflict) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Update violates constraints.");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight cannot be deleted due to references.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete flight.");
        }
    }

    @GetMapping("/flight/{id}/aircraft")
    public ResponseEntity<Aircraft> getAircraftByFlightId(@PathVariable long id) {
        return flightService.getFlightById(id)
                .map(flight -> ResponseEntity.ok(flight.getAircraft()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/flight/{id}/departure-airport")
    public ResponseEntity<Airport> getDepartureAirportByFlightId(@PathVariable long id) {
        return flightService.getFlightById(id)
                .map(flight -> ResponseEntity.ok(flight.getDepartureAirport()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/flight/{id}/arrival-airport")
    public ResponseEntity<Airport> getArrivalAirportByFlightId(@PathVariable long id) {
        return flightService.getFlightById(id)
                .map(flight -> ResponseEntity.ok(flight.getArrivalAirport()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/flight/{id}/gate")
    public ResponseEntity<Gate> getGateByFlightId(@PathVariable long id) {
        return flightService.getFlightById(id)
                .map(flight -> ResponseEntity.ok(flight.getGate()))
                .orElse(ResponseEntity.notFound().build());
    }

    // -------- helpers --------
    private String str(Object o) { return o == null ? null : String.valueOf(o); }
    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

    private Long extractId(Map<String, Object> body, String flatKey, String nestedKey) {
        Object v = body.get(flatKey);
        if (v != null) {
            try { return Long.valueOf(String.valueOf(v)); } catch (NumberFormatException ignored) {}
        }
        Object nested = body.get(nestedKey);
        if (nested instanceof Map<?,?> m) {
            Object id = m.get("id");
            if (id != null) {
                try { return Long.valueOf(String.valueOf(id)); } catch (NumberFormatException ignored) {}
            }
        }
        return null;
    }
}
