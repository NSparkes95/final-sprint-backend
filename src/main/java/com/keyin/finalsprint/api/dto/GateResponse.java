package com.keyin.finalsprint.api.dto;

public class GateResponse {

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

    // Getters only
    public Long getId() { return id; }
    public String getCode() { return code; }
    public Long getAirportId() { return airportId; }
    public String getAirportName() { return airportName; }

    public Object getAirport() {
        return new Object() {
            public Long getId() { return airportId; }
            public String getName() { return airportName; }
        };
    }
}
