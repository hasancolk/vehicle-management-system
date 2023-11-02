package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPermissionDto {

    private Long groupId;
    private String groupName;
    private Role role;

    public GroupPermissionDto(Long groupId, String groupName, Role role) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.role = role;
    }

}
