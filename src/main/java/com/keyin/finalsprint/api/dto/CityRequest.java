package com.keyin.finalsprint.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;

    @NotBlank(message = "Province is required")
    @Size(max = 100, message = "Province can be at most 100 characters")
    private String province;

    @Min(value = 0, message = "Population must be zero or greater")
    private int population;

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
}

