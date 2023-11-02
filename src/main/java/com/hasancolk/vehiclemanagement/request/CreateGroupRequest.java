package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequest {

    @NotBlank(message = "groupName cannot be null or empty")
    @Size(min = 1, max = 50, message = "groupName must be between 1 and 50 characters")
    private String groupName;

    private Long parentGroupId;
}
