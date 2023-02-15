package com.bettersounds.dto;

import com.bettersounds.constant.AuthProvider;
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
public class SignupRequest {
    
    private String imageUrl;
 
    private String name;
    
//    private String providerUserId;
 
    private String email;
 
//    private AuthProvider authProvider;
    
    private String password;
 
    private String matchingPassword;
 
//    public SignupRequest(String imageUrl, String name, String email, String providerUserId) {
//        this.imageUrl = imageUrl;
//        this.name = name;
//        this.email = email;
//        this.providerUserId = providerUserId;
//    }  
//    
//    public SignupRequest(String name, String email, String password, String matchingPassword, AuthProvider authProvider) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.matchingPassword = matchingPassword;
//    }  
}
