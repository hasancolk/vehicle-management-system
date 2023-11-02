package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.entity.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupedVehiclesDto {
    private Long groupId;
    private String groupName;
    private List<Vehicle>vehicles;
}