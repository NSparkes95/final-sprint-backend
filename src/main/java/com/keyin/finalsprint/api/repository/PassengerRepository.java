package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
