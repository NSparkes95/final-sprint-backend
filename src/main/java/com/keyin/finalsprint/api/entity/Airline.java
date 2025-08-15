package com.keyin.finalsprint.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 3, nullable = false)
    private String code; // Airline code like "AC", "UA", etc.

    // One airline can operate many aircraft
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    @JsonManagedReference("airline-aircraft")
    private List<Aircraft> aircraftList;

    public Airline() {}

    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<Aircraft> getAircraftList() { return aircraftList; }
    public void setAircraftList(List<Aircraft> aircraftList) { this.aircraftList = aircraftList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline)) return false;
        Airline airline = (Airline) o;
        return Objects.equals(id, airline.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
