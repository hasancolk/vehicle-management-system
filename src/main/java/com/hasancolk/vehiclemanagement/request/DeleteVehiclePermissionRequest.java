package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteVehiclePermissionRequest {

    @NotNull(message = "userId is required.")
    Long userId;

    @NotNull(message = "vehicleId is required.")
    Long vehicleId;
}
