package com.keyin.finalsprint.api.dto;

import jakarta.validation.constraints.NotBlank;

public class AircraftRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String city;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
