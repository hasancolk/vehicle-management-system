package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.request.AuthenticationRequest;
import com.hasancolk.vehiclemanagement.dto.AuthenticationResponse;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<>(authenticationService.authenticateUser(authenticationRequest), HttpStatus.OK);
    }

}
