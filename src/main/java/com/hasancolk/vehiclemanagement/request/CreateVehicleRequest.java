package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVehicleRequest {

    @NotBlank(message = "plate cannot be null or empty")
    @Size(min = 1, max = 50, message = "plate must be between 1 and 50 characters")
    private String plate;

    @Size(min = 1, max = 50, message = "chassisNo must be between 1 and 50 characters")
    private String chassisNo;

    @Size(min = 1, max = 50, message = "label must be between 1 and 50 characters")
    private String label;

    @NotBlank(message = "brand cannot be null or empty")
    @Size(min = 1, max = 50, message = "brand must be between 1 and 50 characters")
    private String brand;

    @NotBlank(message = "model cannot be null or empty")
    @Size(min = 1, max = 50, message = "model must be between 1 and 50 characters")
    private String model;

    @NotNull(message = "modelYear cannot be null")
    private Integer modelYear;
}
