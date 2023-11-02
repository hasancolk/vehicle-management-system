package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.entity.UserGroupPermission;
import com.hasancolk.vehiclemanagement.dto.GroupPermissionDto;
import com.hasancolk.vehiclemanagement.dto.UserGroupPermissionRoleDto;
import com.hasancolk.vehiclemanagement.request.DeleteGroupPermissionRequest;
import com.hasancolk.vehiclemanagement.request.UpsertGroupPermissionRequest;

import java.util.List;

public interface UserGroupPermissionService {
    UserGroupPermission createPermission(UpsertGroupPermissionRequest upsertGroupPermissionRequest);
    void deletePermission(DeleteGroupPermissionRequest deleteGroupPermissionRequest);
    UserGroupPermission updatePermissionRole(UpsertGroupPermissionRequest upsertGroupPermissionRequest);
    List<GroupPermissionDto>fetchUserGroupPermissionsByUserId(Long userId);
    List<UserGroupPermissionRoleDto>fetchUserGroupPermissionsByGroupId(Long groupId);
}
