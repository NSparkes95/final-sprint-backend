package com.keyin.s4sprint1.api.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents an airport belonging to a city and used by multiple aircraft.
 */
@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    // Many airports belong to one city
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    // Many aircraft can use many airports (for takeoff/landing)
    @ManyToMany(mappedBy = "airports")
    private List<Aircraft> aircraftList;

    //Constructor
    public Airport() {}

    public Airport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
