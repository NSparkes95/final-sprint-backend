package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.Flight;
import com.keyin.finalsprint.api.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setFlightNumber(updatedFlight.getFlightNumber());
                    flight.setAircraft(updatedFlight.getAircraft());
                    flight.setDepartureAirport(updatedFlight.getDepartureAirport());
                    flight.setArrivalAirport(updatedFlight.getArrivalAirport());
                    flight.setGate(updatedFlight.getGate());
                    return flightRepository.save(flight);
                })
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
