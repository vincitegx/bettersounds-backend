package com.bettersounds.services;

import com.bettersounds.domain.RefreshToken;

/**
 *
 * @author TEGA
 */
public interface RefreshTokenService {
    
    RefreshToken generateRefreshToken();
    
    void validateRefreshToken(String token); 
    
    Boolean deleteRefreshToken(String token);
}
