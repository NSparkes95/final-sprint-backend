package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Flight;
import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @PostMapping("/flight")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight newFlight) {
        Flight created = flightService.createFlight(newFlight);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/flight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight updatedFlight) {
        try {
            Flight flight = flightService.updateFlight(id, updatedFlight);
            return ResponseEntity.ok(flight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
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
}
