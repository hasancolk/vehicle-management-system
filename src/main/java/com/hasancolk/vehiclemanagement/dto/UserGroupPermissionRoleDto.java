package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupPermissionRoleDto {

    private Long userId;
    private String username;
    private Role role;

    public UserGroupPermissionRoleDto(Long userId, String username, Role role){
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

}
