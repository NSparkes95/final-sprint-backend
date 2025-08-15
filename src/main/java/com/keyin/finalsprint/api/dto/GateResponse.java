package com.keyin.finalsprint.api.dto;

import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.Gate;

public class GateResponse extends Gate {

    private Long id;
    private String code;
    private Long airportId;
    private String airportName;

    public GateResponse(Long id, String code, Long airportId, String airportName) {
        this.id = id;
        this.code = code;
        this.airportId = airportId;
        this.airportName = airportName;
    }

    public GateResponse(Long id, String code, Class<?> aClass) {
    }

    // Getters only
    public Long getId() { return id; }
    public String getCode() { return code; }
    public Long getAirportId() { return airportId; }
    public String getAirportName() { return airportName; }

    public Airport getAirport() {
        return new Airport() {
            public Long getId() { return airportId; }

            public String getName() { return airportName; }
        };
    }
}
