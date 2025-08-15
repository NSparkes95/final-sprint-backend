package com.keyin.finalsprint.api.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

public class PassengerResponse {

    @Schema(description = "Unique passenger ID", example = "10")
    private Long id;

    @Schema(description = "First name of the passenger", example = "John")
    private String firstName;

    @Schema(description = "Last name of the passenger", example = "Doe")
    private String lastName;

    @Schema(description = "Passenger's phone number", example = "+1-709-555-1234")
    private String phoneNumber;

    @Schema(description = "Passenger's email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "City ID where passenger resides", example = "1")
    private Long cityId;

    @Schema(description = "City name where passenger resides", example = "St. John's")
    private String cityName;

    @Schema(description = "List of aircraft types passenger can fly on", example = "[\"Boeing 737\", \"Airbus A320\"]")
    private List<String> aircraftTypes;


    public PassengerResponse() {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cityId = cityId;
        this.cityName = cityName;
        this.aircraftTypes = aircraftTypes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCityId() {
        return cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getAircraftTypes() {
        return aircraftTypes;
    }

    public void setAircraftTypes(List<String> aircraftTypes) {
        this.aircraftTypes = aircraftTypes;
    }
}