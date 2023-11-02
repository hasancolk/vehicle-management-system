package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.entity.User;
import com.hasancolk.vehiclemanagement.enums.Role;
import com.hasancolk.vehiclemanagement.repository.UserRepository;
import com.hasancolk.vehiclemanagement.dto.UserDto;
import com.hasancolk.vehiclemanagement.dto.UserRegistrationDto;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.request.AddUserToCompanyRequest;
import com.hasancolk.vehiclemanagement.request.CreateUserRequest;
import com.hasancolk.vehiclemanagement.request.UpdateUserRoleRequest;
import com.hasancolk.vehiclemanagement.service.CheckService;
import com.hasancolk.vehiclemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckService checkService;

    @Override
    public UserRegistrationDto createUser(CreateUserRequest createUserRequest) {

        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();
        String name = createUserRequest.getName();
        String surname = createUserRequest.getSurname();

        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        user.setSurname(surname);
        return new UserRegistrationDto(userRepository.save(user));
    }

    @Override
    public UserDto addUserToCompany(Long companyId, AddUserToCompanyRequest addUserToCompanyRequest) {

        Long userId = addUserToCompanyRequest.getUserId();
        Role role = Role.fromValue(addUserToCompanyRequest.getRoleId());

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new EntityNotFoundException("The user was not found!");
        }

        checkService.checkUserIsNotInCompany(userId);

        user.get().setCompanyId(companyId);
        user.get().setRole(role);

        return new UserDto(userRepository.save(user.get()));
    }

    @Override
    public UserDto updateUserRole(UpdateUserRoleRequest updateUserRoleRequest) {

        Long userId = updateUserRoleRequest.getUserId();
        Role role = Role.fromValue(updateUserRoleRequest.getRoleId());

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new EntityNotFoundException("The user was not found!");
        }

        user.get().setRole(role);

        return new UserDto(userRepository.save(user.get()));
    }

    @Override
    public void removeUserFromCompanyByUserId(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new EntityNotFoundException("The user was not found!");
        }

        user.get().setCompanyId(null);
        user.get().setRole(null);
        userRepository.save(user.get());
    }

    @Override
    public List<UserDto> fetchAllUsersByCompanyId(Long companyId) {
        return userRepository.findAllUsersByCompanyId(companyId);
    }

    @Override
    public UserDto fetchUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}
