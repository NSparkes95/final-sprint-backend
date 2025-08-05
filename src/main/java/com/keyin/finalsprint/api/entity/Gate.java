package com.keyin.finalsprint.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    // Many gates belong to one airport
    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = false)
    @JsonBackReference
    private Airport airport;

    //Constructor
    public Gate() {}
    public Gate(String code, Airport airport) {
        this.code = code;
        this.airport = airport;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Airport getAirport() { return airport; }
    public void setAirport(Airport airport) { this.airport = airport; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gate gate)) return false;
        return Objects.equals(id, gate.id) && Objects.equals(code, gate.code) && Objects.equals(airport, gate.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, airport);
    }
}
