package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.request.DeleteVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.dto.IndependentVehicleDto;
import com.hasancolk.vehiclemanagement.dto.UserVehiclePermissionDto;
import com.hasancolk.vehiclemanagement.request.UpsertVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.entity.UserVehiclePermission;

import java.util.List;

public interface UserVehiclePermissionService {
    UserVehiclePermission createPermission(UpsertVehiclePermissionRequest upsertVehiclePermissionRequest);
    UserVehiclePermission updatePermissionRole(UpsertVehiclePermissionRequest upsertVehiclePermissionRequest);
    void deletePermission(DeleteVehiclePermissionRequest deleteVehiclePermissionRequest);
    List<IndependentVehicleDto> fetchUserVehiclePermissionsByUserId(Long userId);
    List<UserVehiclePermissionDto> fetchUserVehiclePermissionsByVehicleId(Long vehicleId);
}
