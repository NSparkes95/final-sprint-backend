package com.keyin.finalsprint.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight number is mandatory")
    private String flightNumber;

    // Many flights use one aircraft
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    @JsonBackReference
    private Aircraft aircraft;

    // Many flights depart from one airport
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    @JsonBackReference
    private Airport departureAirport;

    // Many flights arrive at one airport
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    @JsonBackReference
    private Airport arrivalAirport;

    // Many flights use one gate (departure)
    @ManyToOne
    @JoinColumn(name = "gate_id")
    @JsonBackReference
    @NotNull(message = "Gate is mandatory")
    private Gate gate;

    // Constructors
    public Flight() {}

    public Flight(String flightNumber, Aircraft aircraft, Airport departureAirport, Airport arrivalAirport, Gate gate) {
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.gate = gate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public Airport getDepartureAirport() { return departureAirport; }
    public void setDepartureAirport(Airport departureAirport) { this.departureAirport = departureAirport; }

    public Airport getArrivalAirport() { return arrivalAirport; }
    public void setArrivalAirport(Airport arrivalAirport) { this.arrivalAirport = arrivalAirport; }

    public Gate getGate() { return gate; }
    public void setGate(Gate gate) { this.gate = gate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
