package com.bettersounds.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentNG implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double price;
    
    private String currency;
    
    private String method;
    
    private String intent;
    
    private String description;  
    
    @OneToOne(cascade = CascadeType.ALL)
    private Order order;
}
