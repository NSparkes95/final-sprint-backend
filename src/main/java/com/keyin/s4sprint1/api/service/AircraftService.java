package com.keyin.s4sprint1.api.service;

import com.keyin.s4sprint1.api.entity.Aircraft;
import com.keyin.s4sprint1.api.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Optional<Aircraft> getAircraftById(Long id) {
        return aircraftRepository.findById(id);
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        return aircraftRepository.findById(id)
                .map(aircraft -> {
                    aircraft.setType(updatedAircraft.getType());
                    aircraft.setAirlineName(updatedAircraft.getAirlineName());
                    aircraft.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
                    aircraft.setAirports(updatedAircraft.getAirports());
                    return aircraftRepository.save(aircraft);
                })
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }
}

