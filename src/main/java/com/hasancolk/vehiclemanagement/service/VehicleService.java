package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.request.CreateVehicleRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleDetailsRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleGroupRequest;
import com.hasancolk.vehiclemanagement.entity.Vehicle;

import java.util.List;


public interface VehicleService {

    Vehicle createVehicle(CreateVehicleRequest createVehicleRequest, Long companyId);
    Vehicle updateVehicleDetails(UpdateVehicleDetailsRequest updateVehicleDetailsRequest);
    Vehicle updateVehicleGroup(UpdateVehicleGroupRequest updateVehicleGroupRequest);
    void deleteVehicle(Long userId, Long vehicleId);
    Vehicle fetchVehicleById(Long vehicleId);
    List<Vehicle> fetchAllCompanyVehiclesByCompanyId(Long companyId);
}
