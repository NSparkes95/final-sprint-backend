package com.keyin.finalsprint.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AircraftResponse {
    private Long id;
    private String name;
    private String code;
    private Long cityId;
    private String cityName;

    public AircraftResponse() {}

    public AircraftResponse(Long id, String name, String code, Long cityId, String cityName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public static class AirlineRequest {
        @NotBlank(message = "Airline name is required")
        @Size(max = 100, message = "Name can be at most 100 characters")
        private String name;

        @NotBlank(message = "Airline code is required")
        @Size(min = 2, max = 3, message = "Code must be 2-3 characters")
        private String code;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }
}

