package com.keyin.finalsprint.api.dto;

import com.keyin.finalsprint.api.entity.Airport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for creating or updating an airport")
public class AirportRequest extends Airport {

    @Schema(description = "Airport name", example = "St. John's International Airport")
    @NotBlank(message = "Airport name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;

    @NotBlank(message = "Airport code is required")
    @Size(min = 3, max = 3, message = "Code must be exactly 3 characters")
    private String code;

    @NotNull(message = "City ID is required")
    private Long cityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}