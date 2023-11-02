package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGroupNameRequest {

    @NotNull(message = "groupId cannot be null")
    private Long groupId;

    @Size(min = 1, max = 50, message = "groupName must be between 1 and 50 characters")
    private String groupName;

}
