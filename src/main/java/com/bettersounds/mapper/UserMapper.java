package com.bettersounds.mapper;

import com.bettersounds.constant.DefaultRoles;
import com.bettersounds.domain.Users;
import com.bettersounds.domain.security.Role;
import com.bettersounds.dto.SignupRequest;
import com.bettersounds.dto.UserInfo;
import com.bettersounds.repository.RoleRepository;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Users.class)
public interface UserMapper {
    
    @Mapping(target = "password", source = "signupRequest.matchingPassword")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "roles", expression = "java(setDefaultRole(roleRepository))")
    Users mapDtoToUser(SignupRequest signupRequest, RoleRepository roleRepository);

    default Set<Role> setDefaultRole(RoleRepository roleRepository){
        Role role = roleRepository.findByName(DefaultRoles.DEFAULT_ROLE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }
    
    @Mapping(target = "roles", source = "userInfo.userRoles")
    Users mapUserInfoToUser(UserInfo userInfo);
    
    UserInfo mapUserToUserInfo(Users user);
}
