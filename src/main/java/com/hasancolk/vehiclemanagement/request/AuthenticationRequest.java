package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank(message = "username cannot be null or empty")
    @Size(min = 1, max = 50, message = "username must be between 1 and 50 characters")
    private String username;

    @NotBlank(message = "password cannot be null or empty")
    @Size(min = 1, max = 50, message = "password must be between 1 and 50 characters")
    private String password;

}
