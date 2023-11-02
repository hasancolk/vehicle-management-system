package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.request.AuthenticationRequest;
import com.hasancolk.vehiclemanagement.dto.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
    UserDetails getCurrentUser(HttpServletRequest request);
    Long getUserId(HttpServletRequest request);
    Long getCompanyIdByUserId(Long userId);
}
