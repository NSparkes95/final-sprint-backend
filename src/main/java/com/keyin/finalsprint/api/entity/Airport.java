package com.keyin.finalsprint.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents an airport belonging to a city and used by multiple aircraft.
 */
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    // Many airports belong to one city
    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference("city-airports")
    private City city;

    // One airport has many gates
    @OneToMany(mappedBy = "airport")
    @JsonManagedReference("airport-gates") // matches the one in Gate.java
    private List<Gate> gates;

    // Many aircraft can use many airports (for takeoff/landing)
    @ManyToMany(mappedBy = "airports")
    @JsonBackReference("aircraft-airport")
    private List<Aircraft> aircraftList;

    // Constructors
    public Airport() {}

    public Airport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public List<Aircraft> getAircraftList() { return aircraftList; }
    public void setAircraftList(List<Aircraft> aircraftList) { this.aircraftList = aircraftList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id) &&
               Objects.equals(name, airport.name) &&
               Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getCityId() {
        return 0;
    }
}
