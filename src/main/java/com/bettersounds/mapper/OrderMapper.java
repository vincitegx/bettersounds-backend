package com.bettersounds.mapper;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.Order;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.BeatDto;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.dto.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Order.class)
public interface OrderMapper {
    
    @Mapping(target = "orderDate", expression = "java(java.time.Instant.now())")  
    @Mapping(target = "userInfo", expression = "java(mapUserToUserInfo(order.getUser()))")
    OrderDto mapToOrderDto(Order order);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", expression = "java(mapUserInfoToUser(orderDto.getUserInfo()))")
    Order mapToOrder(OrderDto orderDto);
    
    UserInfo mapUserToUserInfo(Users user);
    
    Users mapUserInfoToUser(UserInfo userInfo);
}
