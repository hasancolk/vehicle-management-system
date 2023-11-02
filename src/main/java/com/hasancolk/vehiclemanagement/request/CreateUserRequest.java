package com.hasancolk.vehiclemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "username cannot be null or empty")
    @Size(min = 3, max = 30, message = "username must be between 3 and 30 characters")
    private String username;

    @NotBlank(message = "password cannot be null or empty")
    @Size(min = 8, max = 30, message = "password must be between 8 and 30 characters")
    private String password;

    @NotBlank(message = "name cannot be null or empty")
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "surname cannot be null or empty")
    @Size(min = 2, max = 30, message = "surname must be between 2 and 30 characters")
    private String surname;
}