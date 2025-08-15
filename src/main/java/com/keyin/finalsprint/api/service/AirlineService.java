package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.AircraftResponse;
import com.keyin.finalsprint.api.dto.AirlineRequest;
import com.keyin.finalsprint.api.dto.AirlineResponse;
import com.keyin.finalsprint.api.entity.Airline;
import com.keyin.finalsprint.api.repository.AirlineRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<AirlineResponse> getAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<AirlineResponse> getAirlineById(Long id) {
        return airlineRepository.findById(id)
                .map(this::mapToResponse);
    }

    public AirlineResponse createAirline(@Valid AirlineRequest request) {
        Airline airline = new Airline();
        airline.setName(request.getName());
        airline.setCode(request.getCode());
        Airline saved = airlineRepository.save(airline);
        return mapToResponse(saved);
    }

    public AirlineResponse updateAirline(Long id, @Valid AirlineRequest request) {
        return airlineRepository.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setCode(request.getCode());
                    Airline updated = airlineRepository.save(existing);
                    return mapToResponse(updated);
                })
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }

    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }

    private AirlineResponse mapToResponse(Airline airline) {
        return new AirlineResponse(
                airline.getId(),
                airline.getName(),
                airline.getCode(),
                airline.getAircraftList() != null
                        ? airline.getAircraftList().stream()
                        .map(a -> new AircraftResponse(
                                a.getId(),
                                a.getName().toString(),
                                a.getCode().toString(),
                                a.getCity() != null ? a.getCity().getId() : null,
                                a.getCity() != null ? a.getCity().getName() : null
                        ))
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }
}
