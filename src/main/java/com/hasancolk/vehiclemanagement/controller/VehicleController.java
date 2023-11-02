package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.request.CreateVehicleRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleDetailsRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleGroupRequest;
import com.hasancolk.vehiclemanagement.entity.Vehicle;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AuthenticationService authenticationService;


    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @PostMapping("/createVehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody CreateVehicleRequest createVehicleRequest, HttpServletRequest request) {
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(vehicleService.createVehicle(createVehicleRequest,companyId), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#updateVehicleDetailsRequest.vehicleId)")
    @PutMapping("/updateVehicleDetails")
    public ResponseEntity<Vehicle> updateVehicleDetails(@Valid @RequestBody UpdateVehicleDetailsRequest updateVehicleDetailsRequest, HttpServletRequest request) {
        return new ResponseEntity<>(vehicleService.updateVehicleDetails(updateVehicleDetailsRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUpdateVehicleGroupPermission(@authenticationServiceImpl.getUserId(#request),#updateVehicleGroupRequest)")
    @PutMapping("/updateVehicleGroup")
    public ResponseEntity<Vehicle> updateVehicleGroup(@Valid @RequestBody UpdateVehicleGroupRequest updateVehicleGroupRequest, HttpServletRequest request) {
        return new ResponseEntity<>(vehicleService.updateVehicleGroup(updateVehicleGroupRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleAdminPermission(@authenticationServiceImpl.getUserId(#request),#vehicleId)")
    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId, HttpServletRequest request) {
        Long userId = authenticationService.getUserId(request);
        vehicleService.deleteVehicle(userId,vehicleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserVehicleStandardOrAdminPermission(@authenticationServiceImpl.getUserId(#request),#vehicleId)")
    @GetMapping("/fetchVehicleById/{vehicleId}")
    public ResponseEntity<Vehicle> fetchVehicleById(@PathVariable Long vehicleId, HttpServletRequest request){
        return new ResponseEntity<>(vehicleService.fetchVehicleById(vehicleId), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @GetMapping("/fetchAllCompanyVehicles")
    public ResponseEntity<List<Vehicle>> fetchAllCompanyVehicles(HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(vehicleService.fetchAllCompanyVehiclesByCompanyId(companyId), HttpStatus.OK);
    }

}
