package com.bettersounds.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotBlank
    private String email;
 
    @NotBlank
    private String password;
    
}
