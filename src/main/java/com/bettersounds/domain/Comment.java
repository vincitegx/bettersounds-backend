package com.bettersounds.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class Comment implements Serializable {
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @NotEmpty
//    private String text;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Beat beat;
//    
//    private Instant createdDate;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Users user;
    
}
