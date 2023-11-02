package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.enums.Role;
import com.hasancolk.vehiclemanagement.request.DeleteVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.dto.IndependentVehicleDto;
import com.hasancolk.vehiclemanagement.dto.UserVehiclePermissionDto;
import com.hasancolk.vehiclemanagement.request.UpsertVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.entity.UserVehiclePermission;
import com.hasancolk.vehiclemanagement.repository.UserVehiclePermissionRepository;
import com.hasancolk.vehiclemanagement.repository.VehicleRepository;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.service.CheckService;
import com.hasancolk.vehiclemanagement.service.UserVehiclePermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVehiclePermissionServiceImpl implements UserVehiclePermissionService {

    @Autowired
    private UserVehiclePermissionRepository vehiclePermissionRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CheckService checkService;


    @Override
    public UserVehiclePermission createPermission(UpsertVehiclePermissionRequest upsertVehiclePermissionRequest) {

        Long userId = upsertVehiclePermissionRequest.getUserId();
        Long vehicleId = upsertVehiclePermissionRequest.getVehicleId();
        Role role = Role.fromValue(upsertVehiclePermissionRequest.getRoleId());
        Long companyId = vehicleRepository.findCompanyIdByVehicleId(vehicleId);

        checkService.checkUserExistsInCompany(userId, companyId);
        checkService.checkUserIsNotCompanyAdmin(userId, companyId);
        checkService.checkUserIsNotAuthorizedInVehicle(userId, vehicleId);

        UserVehiclePermission userVehiclePermission = new UserVehiclePermission();
        userVehiclePermission.setUserId(userId);
        userVehiclePermission.setVehicleId(vehicleId);
        userVehiclePermission.setRole(role);

        return vehiclePermissionRepository.save(userVehiclePermission);
    }

    @Override
    public UserVehiclePermission updatePermissionRole(UpsertVehiclePermissionRequest upsertVehiclePermissionRequest) {

        Long userId = upsertVehiclePermissionRequest.getUserId();
        Long vehicleId = upsertVehiclePermissionRequest.getVehicleId();
        Role role = Role.fromValue(upsertVehiclePermissionRequest.getRoleId());
        Long companyId = vehicleRepository.findCompanyIdByVehicleId(vehicleId);

        checkService.checkUserExistsInCompany(userId, companyId);

        UserVehiclePermission userVehiclePermission = vehiclePermissionRepository.findByUserIdAndVehicleId(userId,vehicleId); // userGroupPermission'i incele

        if(userVehiclePermission == null){
            throw new EntityNotFoundException("The vehicle permission was not found!");
        }

        userVehiclePermission.setRole(role);
        return vehiclePermissionRepository.save(userVehiclePermission);
    }

    @Transactional
    @Override
    public void deletePermission(DeleteVehiclePermissionRequest deleteVehiclePermissionRequest) {

        Long userId = deleteVehiclePermissionRequest.getUserId();
        Long vehicleId = deleteVehiclePermissionRequest.getVehicleId();
        Long companyId = vehicleRepository.findCompanyIdByVehicleId(vehicleId);

        checkService.checkUserExistsInCompany(userId, companyId);
        checkService.checkUserVehiclePermissionExists(userId, vehicleId);

        vehiclePermissionRepository.deleteByUserIdAndVehicleId(userId,vehicleId);
    }

    @Override
    public List<IndependentVehicleDto> fetchUserVehiclePermissionsByUserId(Long userId) {
        return vehicleRepository.fetchVehiclesByUserId(userId);
    }

    @Override
    public List<UserVehiclePermissionDto> fetchUserVehiclePermissionsByVehicleId(Long vehicleId) {
        return vehiclePermissionRepository.findByVehicleId(vehicleId);
    }

}
