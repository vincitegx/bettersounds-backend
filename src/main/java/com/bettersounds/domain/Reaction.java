package com.bettersounds.domain;

import com.bettersounds.constant.ReactionType;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Reaction implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    private ReactionType reactionType;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Beat beat;
//    
//    private Instant createdDate;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Users user;
}
