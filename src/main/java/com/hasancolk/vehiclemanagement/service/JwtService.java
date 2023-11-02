package com.hasancolk.vehiclemanagement.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String username);
    String extractUsername(String token);
    Boolean validateToken(String token, UserDetails userDetails);
}
