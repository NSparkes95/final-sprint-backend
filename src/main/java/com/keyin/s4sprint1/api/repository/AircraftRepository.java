package com.keyin.s4sprint1.api.repository;

import com.keyin.s4sprint1.api.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends  JpaRepository<Aircraft, Long> {
}
