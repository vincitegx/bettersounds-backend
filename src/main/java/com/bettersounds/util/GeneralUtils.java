package com.bettersounds.util;
import com.bettersounds.constant.AuthProvider;
import com.bettersounds.domain.Users;
import com.bettersounds.domain.security.Role;
import com.bettersounds.dto.LocalUser;
import com.bettersounds.dto.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/**
 *
 * @author TEGA
 */
public class GeneralUtils {
    
//    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        roles.forEach((role) -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return authorities;
//    }
// 
//    public static AuthProvider toSocialProvider(String providerId) {
////        for (AuthProvider socialProvider : AuthProvider.values()) {
////            if (socialProvider.getProviderType().equals(providerId)) {
////                return socialProvider;
////            }
////        }
//        return AuthProvider.LOCAL;
//    }
// 
//    public static UserInfo buildUserInfo(LocalUser localUser) {
////        List<String> roles = LocalUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
////        Users user = localUser.getUser();
//        //return new UserInfo(user.getId().toString(), user.getName(), user.getEmail(), roles);
//        return new UserInfo("", "", "", new ArrayList<>());
//    }
    
}
