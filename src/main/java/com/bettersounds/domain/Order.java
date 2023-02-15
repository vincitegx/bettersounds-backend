package com.bettersounds.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Entity
@Table(name="user_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @Temporal(TemporalType.DATE)
    protected Instant orderDate;
    
    private boolean orderStatus;
    
    private Double orderTotal;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    
    @ManyToOne
    private Users user;
    
    private String orderStatement;

}
