package com.bettersounds.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class RefreshToken implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    
    protected Instant createdDate;
    
}
