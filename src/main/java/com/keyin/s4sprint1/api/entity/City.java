package com.keyin.s4sprint1.api.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a city that contains multiple airports.
 */
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String state;
    private int population;

    // One city can have many airports
    @OneToMany(mappedBy = "city")
    private List<Airport> airports;

    // Constructors
    public City() {}

    public City(String name, String state, int population) {
        this.name = name;
        this.state = state;
        this.population = population;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Airport> getAirports() {
        return airports;
    }
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    // Override equals and hashCode for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return population == city.population &&
               Objects.equals(id, city.id) &&
               Objects.equals(name, city.name) &&
               Objects.equals(state, city.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}