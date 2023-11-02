package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.dto.UserDto;
import com.hasancolk.vehiclemanagement.dto.UserRegistrationDto;
import com.hasancolk.vehiclemanagement.request.AddUserToCompanyRequest;
import com.hasancolk.vehiclemanagement.request.CreateUserRequest;
import com.hasancolk.vehiclemanagement.request.UpdateUserRoleRequest;

import java.util.List;

public interface UserService {
    UserRegistrationDto createUser(CreateUserRequest createUserRequest);
    UserDto addUserToCompany(Long companyId, AddUserToCompanyRequest addUserToCompanyRequest);
    UserDto updateUserRole(UpdateUserRoleRequest updateUserRoleRequest);
    void removeUserFromCompanyByUserId(Long userId);
    List<UserDto> fetchAllUsersByCompanyId(Long companyId);
    UserDto fetchUserById(Long userId);
}
