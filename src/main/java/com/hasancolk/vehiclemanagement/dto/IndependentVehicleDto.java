package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.entity.Vehicle;
import com.hasancolk.vehiclemanagement.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndependentVehicleDto {

    private Vehicle vehicle;
    private Role role;

    public IndependentVehicleDto(Vehicle vehicle, Role role){
        this.vehicle = vehicle;
        this.role = role;
    }

}
