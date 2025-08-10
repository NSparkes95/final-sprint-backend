package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.AirportResponse;
import com.keyin.finalsprint.api.dto.CityRequest;
import com.keyin.finalsprint.api.dto.CityResponse;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cities") // plural is more REST-friendly
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest newCity) {
        CityResponse created = cityService.createCity(newCity);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable long id,
            @Valid @RequestBody CityRequest updatedCity) {
        try {
            CityResponse city = cityService.updateCity(id, updatedCity);
            return ResponseEntity.ok(city);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/airports")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(city -> ResponseEntity.ok(city.getAirports()))
                .orElse(ResponseEntity.notFound().build());
    }
}
