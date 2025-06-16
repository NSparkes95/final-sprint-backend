package com.keyin.s4sprint1.api.service;

import com.keyin.s4sprint1.api.entity.Airport;
import com.keyin.s4sprint1.api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport updatedAirport) {
        return airportRepository.findById(id)
                .map(airport -> {
                    airport.setName(updatedAirport.getName());
                    airport.setCode(updatedAirport.getCode());
                    airport.setCity(updatedAirport.getCity());
                    return airportRepository.save(airport);
                })
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}