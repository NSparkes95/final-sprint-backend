package com.keyin.finalsprint.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object for creating or updating an aircraft")
public class AircraftRequest {
    @Schema(description = "Aircraft name", example = "Boeing 737")
    @NotBlank
    private String name;

    @Schema(description = "Aircraft code", example = "B737")
    @NotBlank
    private String code;

    @Schema(description = "City where aircraft is registered or based", example = "St. John's")
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
