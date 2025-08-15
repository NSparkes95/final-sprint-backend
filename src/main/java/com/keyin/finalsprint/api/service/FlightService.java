package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Flight;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.repository.AircraftRepository;
import com.keyin.finalsprint.api.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        // Ensure aircraft is a managed row to avoid transient errors
        if (flight.getAircraft() != null) {
            Aircraft ac = flight.getAircraft();
            if (ac.getId() != null) {
                Aircraft existing = aircraftRepository.findById(ac.getId())
                        .orElseThrow(() -> new NoSuchElementException("Aircraft not found: " + ac.getId()));
                flight.setAircraft(existing);
            } else {
                flight.setAircraft(aircraftRepository.save(ac));
            }
        }
        // Airports & gate are attached by id only (no fetch needed)
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updates) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Flight not found: " + id));

        // Flight number (only if provided)
        if (updates.getFlightNumber() != null) {
            existing.setFlightNumber(updates.getFlightNumber());
        }

        // ---- Aircraft merge (persist changes to the actual aircraft row) ----
        if (updates.getAircraft() != null) {
            Long incomingAcId = updates.getAircraft().getId();
            Aircraft target;

            if (incomingAcId != null) {
                // Client references a specific aircraft row
                target = aircraftRepository.findById(incomingAcId)
                        .orElseThrow(() -> new NoSuchElementException("Aircraft not found: " + incomingAcId));
            } else if (existing.getAircraft() != null && existing.getAircraft().getId() != null) {
                // Reuse current aircraft row
                target = aircraftRepository.findById(existing.getAircraft().getId())
                        .orElseThrow(() -> new NoSuchElementException("Aircraft not found: " + existing.getAircraft().getId()));
            } else {
                // No aircraft yet → create one
                target = new Aircraft();
            }

            if (updates.getAircraft().getAirlineName() != null) {
                target.setAirlineName(updates.getAircraft().getAirlineName());
            }
            if (updates.getAircraft().getType() != null) {
                target.setType(updates.getAircraft().getType());
            }

            target = aircraftRepository.save(target);
            existing.setAircraft(target);
        }

        // ---- Airports by id (attach references) ----
        Airport dep = updates.getDepartureAirport();
        if (dep != null && dep.getId() != null) {
            Airport ref = new Airport();
            ref.setId(dep.getId());
            existing.setDepartureAirport(ref);
        }

        Airport arr = updates.getArrivalAirport();
        if (arr != null && arr.getId() != null) {
            Airport ref = new Airport();
            ref.setId(arr.getId());
            existing.setArrivalAirport(ref);
        }

        // ---- Gate (allow set or clear) ----
        if (updates.getGate() != null) {
            Gate g = updates.getGate();
            if (g.getId() != null) {
                Gate ref = new Gate();
                ref.setId(g.getId());
                existing.setGate(ref);
            } else {
                existing.setGate(null);
            }
        }

        return flightRepository.save(existing);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
