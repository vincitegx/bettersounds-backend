package com.bettersounds.services;

import com.bettersounds.domain.Users;
import com.bettersounds.dto.JwtAuthResponse;
import com.bettersounds.dto.LoginRequest;
import com.bettersounds.dto.RefreshTokenRequest;
import com.bettersounds.dto.SignupRequest;
import com.bettersounds.exception.UserAlreadyExistAuthenticationException;
import java.util.Optional;


/**
 *
 * @author TEGA
 */
public interface AuthService {
    
    void registerNewUser(SignupRequest signUpRequest) throws UserAlreadyExistAuthenticationException;
    
    void verifyAccount(String token);
    
    Optional<Users> findUserByEmail(String email);
    
    JwtAuthResponse login(LoginRequest loginRequest);
    
    JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    
    boolean isLoggedIn();
    
    Boolean logout(RefreshTokenRequest refreshTokenRequest);
    
    Users getCurrentUser();
}
