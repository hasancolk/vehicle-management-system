package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.entity.Group;
import com.hasancolk.vehiclemanagement.dto.GroupHierarchyWithVehiclesDto;
import com.hasancolk.vehiclemanagement.dto.GroupedVehiclesDto;
import com.hasancolk.vehiclemanagement.request.CreateGroupRequest;
import com.hasancolk.vehiclemanagement.request.UpdateGroupNameRequest;
import com.hasancolk.vehiclemanagement.request.UpdateParentGroupRequest;

import java.util.List;


public interface GroupService {
    Group createGroup(CreateGroupRequest createGroupRequest, Long companyId);
    void deleteGroup(Long groupId);
    Group updateGroupName(UpdateGroupNameRequest updateGroupNameRequest);
    Group updateParentGroup(UpdateParentGroupRequest updateParentGroupRequest);
    List<GroupHierarchyWithVehiclesDto> fetchAllAuthorizedGroupsWithVehiclesByUserId(Long userId);
    List<GroupHierarchyWithVehiclesDto> fetchAllGroupsWithVehiclesByCompanyId(Long companyId);
    GroupedVehiclesDto fetchGroupWithVehiclesByGroupId(Long groupId);
}
