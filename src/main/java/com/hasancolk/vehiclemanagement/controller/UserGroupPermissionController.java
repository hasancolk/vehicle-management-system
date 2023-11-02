package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.entity.UserGroupPermission;
import com.hasancolk.vehiclemanagement.dto.GroupPermissionDto;
import com.hasancolk.vehiclemanagement.dto.UserGroupPermissionRoleDto;
import com.hasancolk.vehiclemanagement.request.DeleteGroupPermissionRequest;
import com.hasancolk.vehiclemanagement.request.UpsertGroupPermissionRequest;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.UserGroupPermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userGroupPermission")
public class UserGroupPermissionController {

    @Autowired
    private UserGroupPermissionService groupPermissionService;

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#upsertGroupPermissionRequest.groupId)")
    @PostMapping("/createPermission")
    public ResponseEntity<UserGroupPermission> createPermission(@Valid @RequestBody UpsertGroupPermissionRequest upsertGroupPermissionRequest, HttpServletRequest request){
        return new ResponseEntity<>(groupPermissionService.createPermission(upsertGroupPermissionRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#deleteGroupPermissionRequest.groupId)")
    @DeleteMapping("/deletePermission")
    public ResponseEntity<Void> deletePermission(@Valid @RequestBody DeleteGroupPermissionRequest deleteGroupPermissionRequest, HttpServletRequest request){
        groupPermissionService.deletePermission(deleteGroupPermissionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#upsertGroupPermissionRequest.groupId)")
    @PutMapping("/updatePermissionRole")
    public ResponseEntity<UserGroupPermission> updatePermissionRole(@Valid @RequestBody UpsertGroupPermissionRequest upsertGroupPermissionRequest, HttpServletRequest request){
        return new ResponseEntity<>(groupPermissionService.updatePermissionRole(upsertGroupPermissionRequest), HttpStatus.OK);
    }

    @GetMapping("/fetchCurrentUserGroupPermissions")
    public ResponseEntity<List<GroupPermissionDto>> fetchCurrentUserGroupPermissions(HttpServletRequest request){
        Long userId = authenticationService.getUserId(request);
        return new ResponseEntity<>(groupPermissionService.fetchUserGroupPermissionsByUserId(userId), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserGroupAdminPermission(@authenticationServiceImpl.getUserId(#request),#groupId)")
    @GetMapping("/fetchUserGroupPermissionsByGroupId/{groupId}")
    public ResponseEntity<List<UserGroupPermissionRoleDto>> fetchGroupPermissionsByGroupId(@PathVariable Long groupId, HttpServletRequest request){
        return new ResponseEntity<>(groupPermissionService.fetchUserGroupPermissionsByGroupId(groupId), HttpStatus.OK);
    }
}