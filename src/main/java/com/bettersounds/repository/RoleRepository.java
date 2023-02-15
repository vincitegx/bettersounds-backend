package com.bettersounds.repository;

import com.bettersounds.domain.security.Role;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
    
}
