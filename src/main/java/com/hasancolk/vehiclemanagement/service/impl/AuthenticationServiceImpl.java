package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.request.AuthenticationRequest;
import com.hasancolk.vehiclemanagement.dto.AuthenticationResponse;
import com.hasancolk.vehiclemanagement.repository.UserRepository;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtService.generateToken(userDetails.getUsername());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setUsername(authenticationRequest.getUsername());
        authenticationResponse.setJwt(jwt);

        return authenticationResponse;
    }

    @Override
    public UserDetails getCurrentUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);
            return userDetailsService.loadUserByUsername(username);
        }
        return null;
    }

    @Override
    public Long getUserId(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);
            return userRepository.findUserIdByUsername(username);
        }
        return null;
    }

    @Override
    public Long getCompanyIdByUserId(Long userId) {
        return userRepository.findCompanyIdByUserId(userId);
    }


}
