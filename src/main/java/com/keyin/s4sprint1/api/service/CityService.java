package com.keyin.s4sprint1.api.service;

import com.keyin.s4sprint1.api.entity.City;
import com.keyin.s4sprint1.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {
        return cityRepository.findById(id)
                .map(city -> {
                    city.setName(updatedCity.getName());
                    city.setState(updatedCity.getState());
                    city.setPopulation(updatedCity.getPopulation());
                    return cityRepository.save(city);
                })
                .orElseThrow(() -> new RuntimeException("City not found"));
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
