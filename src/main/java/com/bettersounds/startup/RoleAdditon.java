package com.bettersounds.startup;

import com.bettersounds.constant.DefaultRoles;
import com.bettersounds.domain.security.Role;
import com.bettersounds.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author TEGA
 */
@Component
@Order(1)
public class RoleAdditon implements CommandLineRunner{    
    
    @Autowired
    RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
//        Role role = new Role();
//        role.setName(DefaultRoles.DEFAULT_ROLE);
//        roleRepository.save(role);
//        roleRepository.flush();
//        role = new Role();
//        role.setName(DefaultRoles.ROLE_ADMIN);
//        roleRepository.save(role);
        
    }
    
}
