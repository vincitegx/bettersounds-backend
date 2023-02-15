package com.bettersounds.services;

import com.bettersounds.dto.OrderDto;

/**
 *
 * @author TEGA
 */
public interface CheckoutService {

    Boolean placeOrder(OrderDto orderDto);
    
}
