package com.bettersounds.services.impl;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.Cart;
import com.bettersounds.domain.CartItem;
import com.bettersounds.domain.Order;
import com.bettersounds.domain.PaymentNG;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.dto.UserInfo;
import com.bettersounds.mapper.OrderMapper;
import com.bettersounds.repository.BeatRepository;
import com.bettersounds.repository.OrderRepository;
import com.bettersounds.services.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@Data
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BeatRepository beatRepository;
//    private final OrderMapper orderMapper;

    @Override
    public synchronized Order createOrder(Cart cart, PaymentNG payment, Users user) {
        Order order = new Order();
        order.setOrderStatus(true);
        Beat beat = new Beat();
        Set<CartItem> cartItemList = cart.getCartItems();
        List<Order> orderForBeat = new ArrayList<>();
        order.setOrderTotal(cart.getGrandTotal());
        order = orderRepository.save(order);
//        for (CartItem cartItem : cartItemList) {
//            beat = beatRepository.findById(cartItem.getBeat().getId()).get();
//            orderForBeat = beat.getOrders();
//        }
//        orderForBeat.add(order);
//        beat.setOrders(orderForBeat);
        beatRepository.saveAndFlush(beat);
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getAllOrders(PageRequest pageRequest) {
        Page<Order> order = orderRepository.findAll(pageRequest);
        Page<OrderDto> orderDto = order.map(this::mapToOrderDto);
        return orderDto;
    }

    public OrderDto mapToOrderDto(Order order) {
//        return orderMapper.mapToOrderDto(order);
return null;
    }

    public Order mapToOrder(OrderDto orderDto) {
//        return orderMapper.mapToOrder(orderDto);
return null;
    }

    public UserInfo mapUserToUserInfo(Users user) {
//        return orderMapper.mapUserToUserInfo(user);
return null;
    }

    public Users mapUserInfoToUser(UserInfo userInfo) {
//        return orderMapper.mapUserInfoToUser(userInfo);
return null;
    }

}
