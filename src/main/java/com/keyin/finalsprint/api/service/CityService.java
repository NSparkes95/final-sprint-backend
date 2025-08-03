package com.keyin.finalsprint.api.service;

import com.keyin.finalsprint.api.entity.City;
import com.keyin.finalsprint.api.repository.CityRepository;
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
                    city.setProvince(updatedCity.getProvince());
                    city.setPopulation(updatedCity.getPopulation());
                    return cityRepository.save(city);
                })
                .orElseThrow(() -> new RuntimeException("City not found"));
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
