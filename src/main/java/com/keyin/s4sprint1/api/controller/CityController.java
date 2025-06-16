package com.keyin.s4sprint1.api.controller;

import com.keyin.s4sprint1.api.entity.Airport;
import com.keyin.s4sprint1.api.entity.City;
import com.keyin.s4sprint1.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/city")
    public ResponseEntity<City> createCity(@RequestBody City newCity) {
        City created = cityService.createCity(newCity);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City updatedCity) {
        try {
            City city = cityService.updateCity(id, updatedCity);
            return ResponseEntity.ok(city);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsByCityId(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(city -> ResponseEntity.ok(city.getAirports()))
                .orElse(ResponseEntity.notFound().build());
    }

}

