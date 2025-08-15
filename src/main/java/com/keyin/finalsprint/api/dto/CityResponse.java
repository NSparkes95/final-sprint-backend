package com.keyin.finalsprint.api.dto;

import java.util.List;

public class CityResponse {

    private Long id;
    private String name;
    private String province;
    private int population;
    private List<AirportResponse> airports;

    public CityResponse() {
    }

    public CityResponse(Long id, String name, String province, int population) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.population = population;
    }

    public CityResponse(Long id, String name, String province, int population, List<AirportResponse> airports) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.population = population;
        this.airports = airports;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public List<AirportResponse> getAirports() {
        return airports;
    }
    public void setAirports(List<AirportResponse> airports) {
        this.airports = airports;
    }
}
