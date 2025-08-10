package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.dto.PassengerRequest;
import com.keyin.finalsprint.api.dto.PassengerResponse;
import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.City;
import com.keyin.finalsprint.api.entity.Passenger;
import com.keyin.finalsprint.api.repository.AircraftRepository;
import com.keyin.finalsprint.api.repository.CityRepository;
import com.keyin.finalsprint.api.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final CityRepository cityRepository;
    private final AircraftRepository aircraftRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, CityRepository cityRepository, AircraftRepository aircraftRepository) {
        this.passengerRepository = passengerRepository;
        this.cityRepository = cityRepository;
        this.aircraftRepository = aircraftRepository;
    }

    public List<PassengerResponse> getAllPassengers() {
        return passengerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<PassengerResponse> getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(this::mapToResponse);
    }

    public PassengerResponse createPassenger(PassengerRequest request) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setPhoneNumber(request.getPhoneNumber());
        passenger.setEmail(request.getEmail());

        // Link to city
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        passenger.setCity(city);

        // Link to aircraft
        if (request.getAircraftIds() != null) {
            List<Aircraft> aircraftList = aircraftRepository.findAllById(request.getAircraftIds());
            passenger.setAircraftList(aircraftList);
        }

        Passenger saved = passengerRepository.save(passenger);
        return convertToResponse(saved);
    }

    private PassengerResponse convertToResponse(Passenger passenger) {
        PassengerResponse response = new PassengerResponse();
        response.setId(passenger.getId());
        response.setFirstName(passenger.getFirstName());
        response.setLastName(passenger.getLastName());
        response.setPhoneNumber(passenger.getPhoneNumber());
        response.setEmail(passenger.getEmail());
        response.setCityId(passenger.getCity().getId());
        response.setCityName(passenger.getCity().getName());

        if (passenger.getAircraftList() != null) {
            List<String> aircraftTypes = passenger.getAircraftList()
                    .stream()
                    .map(Aircraft::getType)
                    .collect(Collectors.toList());
            response.setAircraftTypes(aircraftTypes);
        }
        return response;

    }

    public PassengerResponse updatePassenger(Long id, PassengerRequest request) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    passenger.setFirstName(request.getFirstName());
                    passenger.setLastName(request.getLastName());
                    passenger.setEmail(request.getEmail());
                    passenger.setPhoneNumber(request.getPhoneNumber());

                    City city = cityRepository.findById(request.getCityId())
                            .orElseThrow(() -> new RuntimeException("City not found"));
                    passenger.setCity(city);

                    if (request.getAircraftIds() != null) {
                        List<Aircraft> aircraftList = aircraftRepository.findAllById(request.getAircraftIds());
                        passenger.setAircraftList(aircraftList);
                    }

                    return convertToResponse(passengerRepository.save(passenger));
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    private PassengerResponse mapToResponse(Passenger passenger) {
        return convertToResponse(passenger);

    }

    public Optional<Object> getPassengerAircraftNames(long id) {
        return passengerRepository.findById(id)
                .map(passenger -> passenger.getAircraftList()
                        .stream()
                        .map(Aircraft::getName)
                        .collect(Collectors.toList()));
    }


    public Optional<List<String>> getPassengerAirportNames(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .map(passenger -> {
                    Set<String> airportNames = new HashSet<>();
                    for (Aircraft aircraft : passenger.getAircraftList()) {
                        for (Airport airport : aircraft.getAirports()) {
                            airportNames.add(airport.getName());
                        }
                    }
                    return new ArrayList<>(airportNames);
                });
    }
}

