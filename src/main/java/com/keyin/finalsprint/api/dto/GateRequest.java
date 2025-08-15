package com.keyin.finalsprint.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GateRequest {
    @NotBlank(message = "Gate code is required")
    @Size(min = 1, max = 10, message = "Gate code must be between 1 and 10 characters")
    private String code;
    private Long airportId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }
}
