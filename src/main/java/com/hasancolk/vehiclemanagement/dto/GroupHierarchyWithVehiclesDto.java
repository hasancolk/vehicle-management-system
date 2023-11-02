package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.entity.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupHierarchyWithVehiclesDto {
    private Long groupId;
    private String groupName;
    private List<Vehicle>vehicles;
    private List<GroupHierarchyWithVehiclesDto> childGroups;
}
