package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCompanyRequest {

    @NotBlank(message = "companyName cannot be null or empty")
    @Size(min = 1, max = 50, message = "companyName must be between 1 and 50 characters")
    private String companyName;

    @NotBlank(message = "industry cannot be null or empty")
    @Size(min = 1, max = 50, message = "industry must be between 1 and 50 characters")
    private String industry;
}
