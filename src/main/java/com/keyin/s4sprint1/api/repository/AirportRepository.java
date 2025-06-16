package com.keyin.s4sprint1.api.repository;

import com.keyin.s4sprint1.api.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
