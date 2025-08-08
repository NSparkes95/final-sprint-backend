package com.keyin.finalsprint.api.dto;

public class GateRequest {
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
