package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.GateRequest;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.repository.AirportRepository;
import com.keyin.finalsprint.api.repository.GateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Optional<Gate> getGateById(Long id) {
        return gateRepository.findById(id);
    }

    public Gate createGate(GateRequest gateRequest) {
        Gate gate = new Gate();
        gate.setCode(gateRequest.getCode());

        Airport airport = airportRepository.findById(gateRequest.getAirportId())
                .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + gateRequest.getAirportId()));

        gate.setAirport(airport);

        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, GateRequest gateRequest) {
        return gateRepository.findById(id)
                .map(gate -> {
                    gate.setCode(gateRequest.getCode());
                    Airport airport = airportRepository.findById(gateRequest.getAirportId())
                            .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + gateRequest.getAirportId()));
                    gate.setAirport(airport);
                    return gateRepository.save(gate);
                })
                .orElseThrow(() -> new RuntimeException("Gate not found"));
    }

    public void deleteGate(Long id) {
        gateRepository.deleteById(id);
    }
}
