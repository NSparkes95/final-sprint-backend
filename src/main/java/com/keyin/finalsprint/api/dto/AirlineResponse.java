package com.keyin.finalsprint.api.dto;

import java.util.List;

public class AirlineResponse {
    private Long id;
    private String name;
    private String code;
    private List<AircraftResponse> aircraft;

    public AirlineResponse() {}

    public AirlineResponse(Long id, String name, String code, List<AircraftResponse> aircraft) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.aircraft = aircraft;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<AircraftResponse> getAircraft() { return aircraft; }
    public void setAircraft(List<AircraftResponse> aircraft) { this.aircraft = aircraft; }
}

