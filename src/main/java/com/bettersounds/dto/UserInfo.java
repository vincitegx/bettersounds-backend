package com.bettersounds.dto;

import com.bettersounds.domain.security.Role;
import java.util.Set;
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
public class UserInfo {
    
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Set<Role> userRoles;
    
}
