package com.bettersounds.services.impl;

import com.bettersounds.domain.CartItem;
import com.bettersounds.domain.Order;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.dto.UserInfo;
import com.bettersounds.repository.BeatRepository;
import com.bettersounds.repository.CartItemRepository;
import com.bettersounds.repository.CartRepository;
import com.bettersounds.repository.OrderRepository;
import com.bettersounds.services.CheckoutService;
import com.bettersounds.services.MailService;
import static java.lang.Boolean.TRUE;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;
//    private final BeatMapper beatMapper;
    private final CartRepository cartRepository;
    private final MailService mailService;
    private final CartItemRepository cartItemRepository;
    private final BeatRepository beatRepository;

    @Override
    public Boolean placeOrder(OrderDto orderDto) {
        Boolean orderTranscationSuccessful;
        Set<CartItem> cartItemList = orderDto.getCart().getCartItems();
        cartItemList.forEach(cartItem -> {
            cartItemRepository.save(cartItem);
        });
        orderDto.setOrderStatus(true);
//        orderRepository.save(orderMapper.mapToOrder(orderDto));
        orderRepository.save(mapToOrder(orderDto));
        log.info(""+orderDto);
        orderTranscationSuccessful = TRUE;
        //send order to mail
//        mailService.sendMail(notificationEmail);
        return orderTranscationSuccessful;
    }
    
    private Order mapToOrder(OrderDto orderDto){
        return Order.builder()
                .cart(orderDto.getCart())
                .orderDate(Instant.now())
                .orderStatus(true)
                .orderTotal(orderDto.getOrderTotal())
                .user(mapUserInfoToUser(orderDto.getUserInfo()))
                .build();
    }

    private Users mapUserInfoToUser(UserInfo userInfo) {
        return Users.builder()
                .id(userInfo.getId())
                .email(userInfo.getEmail())
                .roles(userInfo.getUserRoles())
                .build();
    }
}
