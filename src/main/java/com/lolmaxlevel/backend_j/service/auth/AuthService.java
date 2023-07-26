package com.lolmaxlevel.backend_j.service.auth;

import com.lolmaxlevel.backend_j.dto.JwtRequest;
import com.lolmaxlevel.backend_j.dto.JwtResponse;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;

public interface AuthService {
    JwtResponse login(@NonNull JwtRequest request) throws AuthException;

    JwtResponse refreshAccessToken(@NonNull String refreshToken) throws AuthException;
}
