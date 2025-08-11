package com.keyin.finalsprint.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class PassengerRequest {

    @Schema(description = "First name of the passenger", example = "John")
    @NotBlank
    private String firstName;

    @Schema(description = "Last name of the passenger", example = "Doe")
    @NotBlank
    private String lastName;

    @Schema(description = "Passenger's email address", example = "john.doe@example.com")
    @NotBlank
    private String email;

    @Schema(description = "Passenger's phone number", example = "+1-709-555-1234")
    @NotBlank
    private String phoneNumber;

    @Schema(description = "ID of the city the passenger is from", example = "1")
    @NotNull
    private Long cityId; // Assuming passenger belongs to a City entity

    @Schema(description = "List of aircraft IDs the passenger can fly on", example = "[1, 2, 3]")
    @NotNull
    private List<Long> aircraftIds;// List of aircraft IDs the passenger can fly on

    // Getters & Setters
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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getCityId() { return cityId; }
    public void setCityId(Long cityId) { this.cityId = cityId; }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Iterable<Long> getAircraftIds() {
        return aircraftIds;
    }
    public void setAircraftIds(Iterable<Long> aircraftIds) {
        this.aircraftIds = (List<Long>) aircraftIds;
    }
}
