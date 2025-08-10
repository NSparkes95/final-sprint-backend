package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.AirportRequest;
import com.keyin.finalsprint.api.dto.AirportResponse;
import com.keyin.finalsprint.api.dto.PassengerRequest;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.City;
import com.keyin.finalsprint.api.repository.AirportRepository;
import com.keyin.finalsprint.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<AirportResponse> getAirportById(Long id) {
        return airportRepository.findById(id)
                .map(this::mapToResponse);
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        Airport airport = new Airport();
        airport.setName(airportRequest.getName());
        airport.setCode(airportRequest.getCode());

        City city = cityRepository.findById(airportRequest.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);
        return mapToResponse(savedAirport);
    }

    public AirportResponse updateAirport(Long id, AirportRequest updatedAirportRequest) {
        return airportRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedAirportRequest.getName());
                    existing.setCode(updatedAirportRequest.getCode());

                    City city = cityRepository.findById(updatedAirportRequest.getCityId())
                            .orElseThrow(() -> new RuntimeException("City not found"));
                    existing.setCity(city);

                    Airport saved = airportRepository.save(existing);
                    return mapToResponse(saved);
                })
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    private AirportResponse mapToResponse(Airport airport) {
        Long cityId = null;
        String cityName = null;

        if (airport.getCity() != null) {
            cityId = airport.getCity().getId();
            cityName = airport.getCity().getName();
        }

        return new AirportResponse(
                airport.getId(),
                airport.getName(),
                airport.getCode(),
                cityId,
                cityName
        );
    }
}