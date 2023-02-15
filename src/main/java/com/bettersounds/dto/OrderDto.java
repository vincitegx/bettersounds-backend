package com.bettersounds.dto;

import com.bettersounds.domain.Cart;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    
    private Long id;
    
    protected Instant orderDate;
    
    private boolean orderStatus;
    
    private Double orderTotal;

    private String orderStatement;
    
    private Cart cart;
    
    private UserInfo userInfo;
    
//    private List<Beat> beats;
//    private PaymentNG payment;
    
}
