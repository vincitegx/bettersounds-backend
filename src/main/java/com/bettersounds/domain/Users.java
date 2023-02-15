package com.bettersounds.domain;

import com.bettersounds.domain.security.Role;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String imageUrl;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Instant createdDate;
    
//    @Column(nullable = false)
//    private Instant modifiedDate;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean isNonLocked = false;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean isEnabled = false;
}
