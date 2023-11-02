package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.entity.Group;
import com.hasancolk.vehiclemanagement.entity.Vehicle;
import com.hasancolk.vehiclemanagement.dto.GroupHierarchyWithVehiclesDto;
import com.hasancolk.vehiclemanagement.dto.GroupedVehiclesDto;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.repository.GroupRepository;
import com.hasancolk.vehiclemanagement.repository.UserGroupPermissionRepository;
import com.hasancolk.vehiclemanagement.repository.UserRepository;
import com.hasancolk.vehiclemanagement.repository.VehicleRepository;
import com.hasancolk.vehiclemanagement.request.CreateGroupRequest;
import com.hasancolk.vehiclemanagement.request.UpdateGroupNameRequest;
import com.hasancolk.vehiclemanagement.request.UpdateParentGroupRequest;
import com.hasancolk.vehiclemanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupPermissionRepository userGroupPermissionRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public Group createGroup(CreateGroupRequest createGroupRequest, Long companyId) {

        String groupName = createGroupRequest.getGroupName();
        Long parentGroupId = createGroupRequest.getParentGroupId();

        Group group = new Group();
        group.setName(groupName);
        group.setCompanyId(companyId);
        group.setParentGroupId(parentGroupId);
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public Group updateGroupName(UpdateGroupNameRequest updateGroupNameRequest) {

        Long groupId = updateGroupNameRequest.getGroupId();
        String groupName = updateGroupNameRequest.getGroupName();

        Optional<Group> group = groupRepository.findById(groupId);

        if(group.isEmpty()){
            throw new EntityNotFoundException("The group was not found!");
        }

        group.get().setName(groupName);

        return groupRepository.save(group.get());
    }

    @Override
    public Group updateParentGroup(UpdateParentGroupRequest updateParentGroupRequest) {
        Long groupId = updateParentGroupRequest.getGroupId();
        Long parentGroupId = updateParentGroupRequest.getParentGroupId();

        Optional<Group> group = groupRepository.findById(groupId);

        if(group.isEmpty()){
            throw new EntityNotFoundException("The group was not found!");
        }

        group.get().setParentGroupId(parentGroupId);

        return groupRepository.save(group.get());
    }

    @Override
    public GroupedVehiclesDto fetchGroupWithVehiclesByGroupId(Long groupId) {

        Optional<Group> group = groupRepository.findById(groupId);

        if(group.isEmpty()){
            throw new EntityNotFoundException("The group was not found!");
        }

        GroupedVehiclesDto groupedVehiclesDto = new GroupedVehiclesDto();
        groupedVehiclesDto.setGroupId(groupId);
        groupedVehiclesDto.setGroupName(group.get().getName());
        groupedVehiclesDto.setVehicles(vehicleRepository.fetchVehiclesByGroupId(groupId));

        return groupedVehiclesDto;
    }

    @Override
    public List<GroupHierarchyWithVehiclesDto> fetchAllAuthorizedGroupsWithVehiclesByUserId(Long userId) {

        List<GroupHierarchyWithVehiclesDto> groupsAndVehicles = new ArrayList<>();
        List<Long> authorizedGroupIdList = userGroupPermissionRepository.findAuthorizedGroupIdListByUserId(userId);

        for(Long groupId: authorizedGroupIdList){
            GroupHierarchyWithVehiclesDto groupedVehiclesDto = createGroupHierarchyWithVehiclesDto(groupId);
            groupsAndVehicles.add(groupedVehiclesDto);
        }
        return groupsAndVehicles;
    }

    @Override
    public List<GroupHierarchyWithVehiclesDto> fetchAllGroupsWithVehiclesByCompanyId(Long companyId) {
        List<GroupHierarchyWithVehiclesDto> groupsAndVehicles = new ArrayList<>();
        List<Long> groupIdList = groupRepository.findAllParentGroupIdListByCompanyId(companyId);

        for(Long groupId: groupIdList){
            GroupHierarchyWithVehiclesDto groupedVehiclesDto = createGroupHierarchyWithVehiclesDto(groupId);
            groupsAndVehicles.add(groupedVehiclesDto);
        }
        return groupsAndVehicles;
    }

    public GroupHierarchyWithVehiclesDto createGroupHierarchyWithVehiclesDto(Long groupId){

        Optional<Group> group = groupRepository.findById(groupId);
        List<Vehicle>vehicles = vehicleRepository.fetchVehiclesByGroupId(groupId);
        List<Group>childGroupList = groupRepository.findByParentGroupId(groupId);

        GroupHierarchyWithVehiclesDto groupedVehiclesDto = new GroupHierarchyWithVehiclesDto();
        groupedVehiclesDto.setGroupId(groupId);
        groupedVehiclesDto.setGroupName(group.get().getName());
        groupedVehiclesDto.setVehicles(vehicles);

        if(!childGroupList.isEmpty()){
            List<GroupHierarchyWithVehiclesDto>childGroups = new ArrayList<>();
            for(Group childGroup: childGroupList){
                childGroups.add(createGroupHierarchyWithVehiclesDto(childGroup.getId()));
            }
            groupedVehiclesDto.setChildGroups(childGroups);
        }
        return groupedVehiclesDto;
    }

}
