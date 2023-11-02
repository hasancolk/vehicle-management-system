package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.request.DeleteVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.dto.IndependentVehicleDto;
import com.hasancolk.vehiclemanagement.dto.UserVehiclePermissionDto;
import com.hasancolk.vehiclemanagement.request.UpsertVehiclePermissionRequest;
import com.hasancolk.vehiclemanagement.entity.UserVehiclePermission;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.UserVehiclePermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userVehiclePermission")
public class UserVehiclePermissionController {

    @Autowired
    private UserVehiclePermissionService vehiclePermissionService;

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#upsertVehiclePermissionRequest.vehicleId)")
    @PostMapping("/createPermission")
    public ResponseEntity<UserVehiclePermission> createPermission(@Valid @RequestBody UpsertVehiclePermissionRequest upsertVehiclePermissionRequest, HttpServletRequest request){
        return new ResponseEntity<>(vehiclePermissionService.createPermission(upsertVehiclePermissionRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#upsertVehiclePermissionRequest.vehicleId)")
    @PutMapping("/updatePermissionRole")
    public ResponseEntity<UserVehiclePermission> updatePermissionRole(@Valid @RequestBody UpsertVehiclePermissionRequest upsertVehiclePermissionRequest, HttpServletRequest request){
        return new ResponseEntity<>(vehiclePermissionService.updatePermissionRole(upsertVehiclePermissionRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#deleteVehiclePermissionRequest.vehicleId)")
    @DeleteMapping("/deletePermission")
    public ResponseEntity<Void> deletePermission(@Valid @RequestBody DeleteVehiclePermissionRequest deleteVehiclePermissionRequest, HttpServletRequest request){
        vehiclePermissionService.deletePermission(deleteVehiclePermissionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fetchCurrentUserVehiclePermissions")
    public ResponseEntity<List<IndependentVehicleDto>> fetchIndependentVehicles(HttpServletRequest request){
        Long userId = authenticationService.getUserId(request);
        return new ResponseEntity<>(vehiclePermissionService.fetchUserVehiclePermissionsByUserId(userId), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#vehicleId)")
    @GetMapping("/fetchUserVehiclePermissionsByVehicleId/{vehicleId}")
    public ResponseEntity<List<UserVehiclePermissionDto>> fetchUserVehiclePermissionsByVehicleId(@PathVariable Long vehicleId, HttpServletRequest request){
        return new ResponseEntity<>(vehiclePermissionService.fetchUserVehiclePermissionsByVehicleId(vehicleId), HttpStatus.OK);
    }

}
