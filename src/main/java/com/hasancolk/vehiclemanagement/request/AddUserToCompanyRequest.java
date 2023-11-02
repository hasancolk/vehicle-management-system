package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserToCompanyRequest {

    @NotNull(message = "userId cannot be null")
    Long userId;

    @Min(value = 1, message = "roleId must be 1 (ADMIN) or 2 (STANDARD)")
    @Max(value = 2, message = "roleId must be 1 (ADMIN) or 2 (STANDARD)")
    @NotNull(message = "role cannot be null")
    Integer roleId;

}
