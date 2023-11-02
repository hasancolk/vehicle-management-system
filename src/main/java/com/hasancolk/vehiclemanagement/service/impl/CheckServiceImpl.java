package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.repository.*;
import com.hasancolk.vehiclemanagement.request.UpdateParentGroupRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleGroupRequest;
import com.hasancolk.vehiclemanagement.exception.AuthorizationException;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupPermissionRepository userGroupPermissionRepository;

    @Autowired
    private UserVehiclePermissionRepository userVehiclePermissionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public boolean checkCreateGroupPermission(Long userId, Long parentGroupId) {

        if(parentGroupId == null){
            if(userRepository.isCompanyAdminByUserId(userId)){
                return true;
            }
            throw new AuthorizationException("The user does not have admin permission in group!");
        }

        checkUserGroupAdminPermission(userId,parentGroupId);
        return true;
    }

    @Override
    public boolean checkUpdateParentGroupPermission(Long userId, UpdateParentGroupRequest updateParentGroupRequest) {

        Long groupId = updateParentGroupRequest.getGroupId();
        Long parentGroupId = updateParentGroupRequest.getParentGroupId();

        checkUserGroupAdminPermission(userId,groupId);

        if(parentGroupId != null){
            checkUserGroupAdminPermission(userId,parentGroupId);
        }

        return true;
    }

    @Override
    public boolean checkUserGroupAdminPermission(Long userId, Long groupId) {

        Long userCompanyId = userRepository.findCompanyIdByUserId(userId);
        Long groupCompanyId = groupRepository.findCompanyIdByGroupId(groupId);

        if(userCompanyId == null || !userCompanyId.equals(groupCompanyId)){
            throw new EntityNotFoundException("No group with groupId: " + groupId + " found in the company");
        }

        if(userRepository.isCompanyAdminByUserIdAndCompanyId(userId,groupCompanyId)){
            return true;
        }

        while(groupId != null) {
            if(userGroupPermissionRepository.isDirectGroupAdmin(userId, groupId)) {
                return true;
            }
            groupId = groupRepository.getParentGroupIdByGroupId(groupId);
        }

        throw new AuthorizationException("The user does not have admin permission in group!");
    }

    @Override
    public boolean checkUserGroupStandardOrAdminPermission(Long userId, Long groupId) {

        Long userCompanyId = userRepository.findCompanyIdByUserId(userId);
        Long groupCompanyId = groupRepository.findCompanyIdByGroupId(groupId);

        if(userCompanyId == null || !userCompanyId.equals(groupCompanyId)){
            throw new EntityNotFoundException("No group with groupId: " + groupId + " found in the company");
        }

        if(userRepository.isCompanyAdminByUserIdAndCompanyId(userId,groupCompanyId)){
            return true;
        }

        while(groupId != null) {
            if(userGroupPermissionRepository.existsByUserIdAndGroupId(userId,groupId)) {
                return true;
            }
            groupId = groupRepository.getParentGroupIdByGroupId(groupId);
        }

        throw new AuthorizationException("The user does not have permission in group!");
    }

    @Override
    public boolean checkUserVehicleAdminPermission(Long userId, Long vehicleId) {

        Long vehicleCompanyId = vehicleRepository.findCompanyIdByVehicleId(vehicleId);
        Long userCompanyId = userRepository.findCompanyIdByUserId(userId);

        if(userCompanyId == null || !userCompanyId.equals(vehicleCompanyId)){
            throw new EntityNotFoundException("No vehicle with vehicleId: " + vehicleId + " found in the company");
        }

        if (!userRepository.isCompanyAdminByUserIdAndCompanyId(userId, vehicleCompanyId) && !userVehiclePermissionRepository.isVehicleAdminByUserIdAndVehicleId(userId, vehicleId)) {
            throw new AuthorizationException("The user does not have admin permission in vehicle!");
        }

        return true;
    }

    @Override
    public boolean checkUserVehicleStandardOrAdminPermission(Long userId, Long vehicleId) {

        Long vehicleCompanyId = vehicleRepository.findCompanyIdByVehicleId(vehicleId);
        Long userCompanyId = userRepository.findCompanyIdByUserId(userId);

        if(userCompanyId == null || userCompanyId.equals(vehicleCompanyId)){
            throw new EntityNotFoundException("No vehicle with vehicleId: " + vehicleId + " found in the company");
        }

        if (!userRepository.isCompanyAdminByUserIdAndCompanyId(userId, vehicleCompanyId) && !userVehiclePermissionRepository.existsByUserIdAndVehicleId(userId, vehicleId)) {
            throw new AuthorizationException("The user does not have permission in vehicle!");
        }

        return true;
    }

    @Override
    public boolean checkUpdateVehicleGroupPermission(Long userId, UpdateVehicleGroupRequest updateVehicleGroupRequest) {

        Long vehicleId = updateVehicleGroupRequest.getVehicleId();
        Long groupId = updateVehicleGroupRequest.getGroupId();

        checkUserVehicleAdminPermission(userId,vehicleId);

        if(groupId != null){
            checkUserGroupAdminPermission(userId,groupId);
        }

        return true;
    }

    @Override
    public boolean checkCompanyAdminByUserIdAndCompanyId(Long userId, Long companyId) {

        if (!userRepository.existsByIdAndCompanyId(userId,companyId)) {
            throw new EntityNotFoundException("The user is not registered with the company");
        }

        if(!userRepository.isCompanyAdminByUserIdAndCompanyId(userId,companyId)){
            throw new AuthorizationException("The user does not have admin permission in company!");
        }
        return true;
    }

    @Override
    public boolean checkCompanyAdminByUserId(Long userId) {
        if(!userRepository.isCompanyAdminByUserId(userId)){
            throw new AuthorizationException("The user does not have admin permission in company!");
        }
        return true;
    }

    @Override
    public void checkUserIsNotInCompany(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new EntityNotFoundException("The user was not found!");
        }
        if(!userRepository.existsByIdAndCompanyIdIsNull(userId)){
            throw new IllegalArgumentException("The user is already registered with a company");
        }
    }

    @Override
    public boolean checkUserExistsInCompany(Long userId, Long companyId) {
        if (!userRepository.existsByIdAndCompanyId(userId,companyId)) {
            throw new EntityNotFoundException("No user with userId: " + userId + " found in the company");
        }
        return true;
    }

    @Override
    public void checkUserIsNotCompanyAdmin(Long userId, Long companyId) {
        if (userRepository.isCompanyAdminByUserIdAndCompanyId(userId, companyId)) {
            throw new AuthorizationException("Company admin cannot be authorized.");
        }
    }

    @Override
    public void checkUserIsNotAuthorizedInGroup(Long userId, Long groupId) {
        if(userGroupPermissionRepository.existsByUserIdAndGroupId(userId,groupId)){
            throw new AuthorizationException("The user is already authorized in this group.");
        }
    }

    @Override
    public void checkUserIsNotAuthorizedInVehicle(Long userId, Long vehicleId) {
        if (userVehiclePermissionRepository.existsByUserIdAndVehicleId(userId, vehicleId)) {
            throw new AuthorizationException("The user is already authorized in this vehicle.");
        }
    }

    @Override
    public void checkUserGroupPermissionExists(Long userId, Long groupId) {
        if(!userGroupPermissionRepository.existsByUserIdAndGroupId(userId,groupId)){
            throw new EntityNotFoundException("The user group permission was not found!");
        }
    }

    @Override
    public void checkUserVehiclePermissionExists(Long userId, Long vehicleId) {
        if (!userVehiclePermissionRepository.existsByUserIdAndVehicleId(userId, vehicleId)) {
            throw new EntityNotFoundException("The user vehicle permission was not found!");
        }
    }

}
