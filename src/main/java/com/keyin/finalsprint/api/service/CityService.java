package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.AirportResponse;
import com.keyin.finalsprint.api.dto.CityRequest;
import com.keyin.finalsprint.api.dto.CityResponse;
import com.keyin.finalsprint.api.entity.City;
import com.keyin.finalsprint.api.repository.CityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityResponse> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<CityResponse> getCityById(Long id) {
        return cityRepository.findById(id)
                .map(this::mapToResponse);
    }

    public CityResponse createCity(@Valid CityRequest request) {
        City city = new City();
        city.setName(request.getName());
        city.setProvince(request.getProvince());
        city.setPopulation(request.getPopulation());

        City saved = cityRepository.save(city);
        return mapToResponse(saved);
    }

    public CityResponse updateCity(Long id, CityRequest request) {
        return cityRepository.findById(id)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setProvince(request.getProvince());
                    existing.setPopulation(request.getPopulation());
                    City updated = cityRepository.save(existing);
                    return mapToResponse(updated);
                })
                .orElseThrow(() -> new RuntimeException("City not found"));
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public Optional<List<?>> getCityAirports(Long id) {
        return cityRepository.findById(id)
                .map(city -> city.getAirports());
    }

    private CityResponse mapToResponse(City city) {
        return new CityResponse(
                city.getId(),
                city.getName(),
                city.getProvince(),
                city.getPopulation(),
                city.getAirports() != null
                        ? city.getAirports().stream()
                        .map(airport -> new AirportResponse(
                                airport.getId(),
                                airport.getName(),
                                airport.getCode(),
                                airport.getCity() != null ? airport.getCity().getId() : null,
                                airport.getCity() != null ? airport.getCity().getName() : null
                        ))
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }
}

