package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
