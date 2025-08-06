package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
