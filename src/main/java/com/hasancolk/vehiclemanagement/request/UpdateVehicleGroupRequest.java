package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVehicleGroupRequest {

    @NotNull(message = "vehicleId cannot be null")
    private Long vehicleId;

    private Long groupId;
}
