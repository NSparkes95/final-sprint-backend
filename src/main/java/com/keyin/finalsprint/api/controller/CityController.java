package com.keyin.finalsprint.api.controller;

import com.keyin.finalsprint.api.dto.AirportResponse;
import com.keyin.finalsprint.api.dto.CityRequest;
import com.keyin.finalsprint.api.dto.CityResponse;
import com.keyin.finalsprint.api.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Operation(summary = "Get all cities", description = "Retrieve a list of all cities in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of cities retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @Operation(summary = "Get city by ID", description = "Retrieve a specific city using its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class))),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new city", description = "Add a new city to the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "City created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest newCity) {
        CityResponse created = cityService.createCity(newCity);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Update a city", description = "Update details of an existing city.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class))),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable long id,
            @Valid @RequestBody CityRequest updatedCity) {
        try {
            CityResponse city = cityService.updateCity(id, updatedCity);
            return ResponseEntity.ok(city);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a city", description = "Remove a city from the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get airports in a city", description = "Retrieve all airports belonging to a specific city.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of airports retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AirportResponse.class))),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @GetMapping("/{id}/airports")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable long id) {
        return cityService.getCityById(id)
                .map(city -> ResponseEntity.ok(city.getAirports()))
                .orElse(ResponseEntity.notFound().build());
    }
}
