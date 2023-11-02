package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.dto.UserDto;
import com.hasancolk.vehiclemanagement.dto.UserRegistrationDto;
import com.hasancolk.vehiclemanagement.request.AddUserToCompanyRequest;
import com.hasancolk.vehiclemanagement.request.CreateUserRequest;
import com.hasancolk.vehiclemanagement.request.UpdateUserRoleRequest;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/createUser")
    public ResponseEntity<UserRegistrationDto>createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @PostMapping("/addUserToCompany")
    public ResponseEntity<UserDto> addUserToCompany(@Valid @RequestBody AddUserToCompanyRequest addUserToCompanyRequest, HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(userService.addUserToCompany(companyId,addUserToCompanyRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserIdAndCompanyId(@authenticationServiceImpl.getUserId(#request),@authenticationServiceImpl.getCompanyIdByUserId(#updateUserRoleRequest.userId))")
    @PutMapping("/updateUserRole")
    public ResponseEntity<UserDto> updateUserRole(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest, HttpServletRequest request){
        return new ResponseEntity<>(userService.updateUserRole(updateUserRoleRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserIdAndCompanyId(@authenticationServiceImpl.getUserId(#request),@authenticationServiceImpl.getCompanyIdByUserId(#userId))")
    @DeleteMapping("/removeUserFromCompanyByUserId/{userId}")
    public ResponseEntity<Void> removeUserFromCompanyByUserId(@PathVariable Long userId, HttpServletRequest request){
        userService.removeUserFromCompanyByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fetchAllCompanyUsers")
    public ResponseEntity<List<UserDto>> fetchAllCompanyUsers(HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(userService.fetchAllUsersByCompanyId(companyId), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserExistsInCompany(@authenticationServiceImpl.getUserId(#request),@authenticationServiceImpl.getCompanyIdByUserId(#userId))")
    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<UserDto> fetchUserById(@PathVariable Long userId, HttpServletRequest request){
        return new ResponseEntity<>(userService.fetchUserById(userId), HttpStatus.OK);
    }

}
