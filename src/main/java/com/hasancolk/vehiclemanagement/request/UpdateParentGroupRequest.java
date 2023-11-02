package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateParentGroupRequest {

    @NotNull(message = "groupId cannot be null")
    private Long groupId;

    private Long parentGroupId;
}
