package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.entity.UserGroupPermission;
import com.hasancolk.vehiclemanagement.enums.Role;
import com.hasancolk.vehiclemanagement.repository.GroupRepository;
import com.hasancolk.vehiclemanagement.repository.UserGroupPermissionRepository;
import com.hasancolk.vehiclemanagement.dto.GroupPermissionDto;
import com.hasancolk.vehiclemanagement.dto.UserGroupPermissionRoleDto;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.request.DeleteGroupPermissionRequest;
import com.hasancolk.vehiclemanagement.request.UpsertGroupPermissionRequest;
import com.hasancolk.vehiclemanagement.service.CheckService;
import com.hasancolk.vehiclemanagement.service.UserGroupPermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupPermissionServiceImpl implements UserGroupPermissionService {

    @Autowired
    private UserGroupPermissionRepository userGroupPermissionRepository;

    @Autowired
    private CheckService checkService;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public UserGroupPermission createPermission(UpsertGroupPermissionRequest upsertGroupPermissionRequest) {

        Long userId = upsertGroupPermissionRequest.getUserId();
        Long groupId = upsertGroupPermissionRequest.getGroupId();
        Role role = Role.fromValue(upsertGroupPermissionRequest.getRoleId());
        Long companyId = groupRepository.findCompanyIdByGroupId(groupId);

        checkService.checkUserExistsInCompany(userId, companyId);
        checkService.checkUserIsNotCompanyAdmin(userId, companyId);
        checkService.checkUserIsNotAuthorizedInGroup(userId, groupId);

        UserGroupPermission userGroupPermission = new UserGroupPermission();
        userGroupPermission.setUserId(userId);
        userGroupPermission.setGroupId(groupId);
        userGroupPermission.setRole(role);
        return userGroupPermissionRepository.save(userGroupPermission);
    }

    @Transactional
    @Override
    public void deletePermission(DeleteGroupPermissionRequest deleteGroupPermissionRequest) {

        Long userId = deleteGroupPermissionRequest.getUserId();
        Long groupId = deleteGroupPermissionRequest.getGroupId();
        Long companyId = groupRepository.findCompanyIdByGroupId(groupId);

        checkService.checkUserExistsInCompany(userId, companyId);
        checkService.checkUserGroupPermissionExists(userId, groupId);

        userGroupPermissionRepository.deleteByUserIdAndGroupId(userId,groupId);
    }

    @Override
    public UserGroupPermission updatePermissionRole(UpsertGroupPermissionRequest upsertGroupPermissionRequest) {

        Long userId = upsertGroupPermissionRequest.getUserId();
        Long groupId = upsertGroupPermissionRequest.getGroupId();
        Role role = Role.fromValue(upsertGroupPermissionRequest.getRoleId());
        Long companyId = groupRepository.findCompanyIdByGroupId(groupId);

        checkService.checkUserExistsInCompany(userId, companyId);

        UserGroupPermission groupPermission = userGroupPermissionRepository.findByUserIdAndGroupId(userId,groupId);

        if(groupPermission == null){
            throw new EntityNotFoundException("The user group permission was not found!");
        }

        groupPermission.setRole(role);
        return userGroupPermissionRepository.save(groupPermission);
    }

    @Override
    public List<GroupPermissionDto> fetchUserGroupPermissionsByUserId(Long userId) {
        return userGroupPermissionRepository.fetchUserGroupPermissionsByUserId(userId);
    }

    @Override
    public List<UserGroupPermissionRoleDto> fetchUserGroupPermissionsByGroupId(Long groupId) {
        return userGroupPermissionRepository.findByGroupId(groupId);
    }

}
