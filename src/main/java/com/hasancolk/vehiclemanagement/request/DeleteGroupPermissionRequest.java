package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteGroupPermissionRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;

    @NotNull(message = "groupId cannot be null")
    private Long groupId;
}
