package com.keyin.finalsprint.api.repository;

import com.keyin.finalsprint.api.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends  JpaRepository<Aircraft, Long> {
}
