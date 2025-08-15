package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
