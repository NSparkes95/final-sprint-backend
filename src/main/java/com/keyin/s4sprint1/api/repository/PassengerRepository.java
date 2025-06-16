package com.keyin.s4sprint1.api.repository;

import com.keyin.s4sprint1.api.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
