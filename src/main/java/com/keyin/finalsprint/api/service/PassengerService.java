package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.Passenger;
import com.keyin.finalsprint.api.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    passenger.setFirstName(updatedPassenger.getFirstName());
                    passenger.setLastName(updatedPassenger.getLastName());
                    passenger.setPhoneNumber(updatedPassenger.getPhoneNumber());
                    passenger.setCity(updatedPassenger.getCity());
                    passenger.setAircraftList(updatedPassenger.getAircraftList());
                    return passengerRepository.save(passenger);
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}

