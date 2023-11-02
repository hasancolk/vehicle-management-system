package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserVehiclePermissionDto {

    private Long userId;
    private String username;
    private Role role;

    public UserVehiclePermissionDto(Long userId, String username, Role role){
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

}
