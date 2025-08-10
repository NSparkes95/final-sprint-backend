package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.GateRequest;
import com.keyin.finalsprint.api.dto.GateResponse;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.repository.AirportRepository;
import com.keyin.finalsprint.api.repository.GateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<GateResponse> getAllGates() {
        return gateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<GateResponse> getGateById(Long id) {
        return gateRepository.findById(id)
                .map(this::mapToResponse);
    }

    public GateResponse createGate(GateRequest request) {
        Airport airport = airportRepository.findById(request.getAirportId())
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        Gate gate = new Gate();
        gate.setCode(request.getCode());
        gate.setAirport(airport);

        return mapToResponse(gateRepository.save(gate));
    }

    public GateResponse updateGate(Long id, GateRequest request) {
        return gateRepository.findById(id)
                .map(gate -> {
                    gate.setCode(request.getCode());
                    Airport airport = airportRepository.findById(request.getAirportId())
                            .orElseThrow(() -> new RuntimeException("Airport not found"));
                    gate.setAirport(airport);
                    return mapToResponse(gateRepository.save(gate));
                })
                .orElseThrow(() -> new RuntimeException("Gate not found"));
    }

    public void deleteGate(Long id) {
        gateRepository.deleteById(id);
    }

    private GateResponse mapToResponse(Gate gate) {
        return new GateResponse(
                gate.getId(),
                gate.getCode(),
                gate.getAirport().getId(),
                gate.getAirport().getName()
        );
    }
}

