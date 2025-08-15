package com.keyin.finalsprint.api.dto;

import com.keyin.finalsprint.api.entity.Airport;

public class AirportResponse extends Airport {
    private Long id;
    private String name;
    private String code;
    private int cityId;
    private String cityName;

    public AirportResponse(Long id, String name, String code, String s) {} // default constructor for JSON serialization

    public AirportResponse(Long id, String name, String code, int cityId, String cityName) {
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

    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
}

