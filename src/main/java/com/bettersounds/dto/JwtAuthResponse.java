package com.bettersounds.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthResponse {
    
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private UserInfo user;
    
    public JwtAuthResponse(String authenticationToken){
        this.authenticationToken = authenticationToken;
    }
}
