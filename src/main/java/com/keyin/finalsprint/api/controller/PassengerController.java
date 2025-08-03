package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Passenger;
import com.keyin.finalsprint.api.service.PassengerService;
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
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/passenger/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/passenger")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger newPassenger) {
        Passenger created = passengerService.createPassenger(newPassenger);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/passenger/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable long id, @RequestBody Passenger updatedPassenger) {
        try {
            Passenger passenger = passengerService.updatePassenger(id, updatedPassenger);
            return ResponseEntity.ok(passenger);
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
    public ResponseEntity<List<Aircraft>> getAircraftByPassengerId(@PathVariable long id) {
        return passengerService.getPassengerById(id)
                .map(passenger -> ResponseEntity.ok(passenger.getAircraftList()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/passenger/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsUsedByPassenger(@PathVariable long id) {
        return passengerService.getPassengerById(id)
                .map(passenger -> {
                    List<Airport> airports = new ArrayList<>();
                    passenger.getAircraftList().forEach(aircraft -> {
                        if (aircraft.getAirports() != null) {
                            for (Airport airport : aircraft.getAirports()) {
                                if (!airports.contains(airport)) {
                                    airports.add(airport);
                                }
                            }
                        }
                    });
                    return ResponseEntity.ok(airports);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}