package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCompanyRequest {

    @Size(min = 1, max = 50, message = "companyName must be between 1 and 50 characters")
    private String companyName;

    @Size(min = 1, max = 50, message = "industry must be between 1 and 50 characters")
    private String industry;

}
