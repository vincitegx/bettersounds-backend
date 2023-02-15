package com.bettersounds.services;

import com.bettersounds.domain.Cart;
import com.bettersounds.domain.Order;
import com.bettersounds.domain.PaymentNG;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author TEGA
 */
public interface OrderService {

    Order createOrder(Cart shoppingCart,PaymentNG payment,Users user);
    
    Page<OrderDto> getAllOrders(PageRequest pageRequest);
}
