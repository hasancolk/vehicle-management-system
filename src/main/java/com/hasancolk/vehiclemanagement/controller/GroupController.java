package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.entity.Group;
import com.hasancolk.vehiclemanagement.dto.GroupHierarchyWithVehiclesDto;
import com.hasancolk.vehiclemanagement.dto.GroupedVehiclesDto;
import com.hasancolk.vehiclemanagement.request.CreateGroupRequest;
import com.hasancolk.vehiclemanagement.request.UpdateGroupNameRequest;
import com.hasancolk.vehiclemanagement.request.UpdateParentGroupRequest;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthenticationService authenticationService;


    @PreAuthorize("@checkServiceImpl.checkCreateGroupPermission(@authenticationServiceImpl.getUserId(#request),#createGroupRequest.parentGroupId)")
    @PostMapping("/createGroup")
    public ResponseEntity<Group> createGroup(@Valid @RequestBody CreateGroupRequest createGroupRequest, HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(groupService.createGroup(createGroupRequest,companyId), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#groupId)")
    @DeleteMapping("/deleteGroup/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId, HttpServletRequest request){
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#updateGroupNameRequest.groupId)")
    @PutMapping("/updateGroupName")
    public ResponseEntity<Group> updateGroupName(@Valid @RequestBody UpdateGroupNameRequest updateGroupNameRequest, HttpServletRequest request){
        return new ResponseEntity<>(groupService.updateGroupName(updateGroupNameRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUpdateParentGroupPermission(@authenticationServiceImpl.getUserId(#request),#updateParentGroupRequest)")
    @PutMapping("/updateParentGroup")
    public ResponseEntity<Group> updateParentGroup(@Valid @RequestBody UpdateParentGroupRequest updateParentGroupRequest, HttpServletRequest request){
        return new ResponseEntity<>(groupService.updateParentGroup(updateParentGroupRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupStandardOrAdminPermission(@authenticationServiceImpl.getUserId(#request),#groupId)")
    @GetMapping("/fetchGroupWithVehiclesByGroupId/{groupId}")
    public ResponseEntity<GroupedVehiclesDto> fetchGroupWithVehiclesByGroupId(@PathVariable Long groupId, HttpServletRequest request){
        return new ResponseEntity<>(groupService.fetchGroupWithVehiclesByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/fetchAllAuthorizedGroupsWithVehicles")
    public ResponseEntity<List<GroupHierarchyWithVehiclesDto>> fetchAllAuthorizedGroupsWithVehicles(HttpServletRequest request){
        Long userId = authenticationService.getUserId(request);
        return new ResponseEntity<>(groupService.fetchAllAuthorizedGroupsWithVehiclesByUserId(userId), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @GetMapping("/fetchAllCompanyGroupsWithVehicles")
    public ResponseEntity<List<GroupHierarchyWithVehiclesDto>> fetchAllCompanyGroupsWithVehicles(HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(groupService.fetchAllGroupsWithVehiclesByCompanyId(companyId), HttpStatus.OK);
    }

}
