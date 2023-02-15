package com.bettersounds.startup;

import com.bettersounds.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.bettersounds.constant.DefaultRoles;
import com.bettersounds.domain.Users;
import com.bettersounds.domain.security.Role;
import com.bettersounds.repository.UserRepository;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.core.annotation.Order;
/**
 *
 * @author TEGA
 */
@Component
@Order(2)
public class UserAddition implements CommandLineRunner{

    @Value("${admin.properties.name}")
    private String name;
    
    @Value("${admin.properties.email}")
    private String email;
    
    @Value("${admin.properties.password}")
    private String password;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
//        Users admin = new Users();
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleRepository.findByName(DefaultRoles.ROLE_ADMIN));
//        admin.setCreatedDate(Instant.now());
//        admin.setEmail(this.email);
//        admin.setIsEnabled(true);
//        admin.setIsNonLocked(true);
//        admin.setName(this.name);
//        admin.setPassword(passwordEncoder.encode(this.password));
//        admin.setModifiedDate(Instant.now());
//        admin.setRoles(roles);
//        userRepository.save(admin);
        
    }
    
}
