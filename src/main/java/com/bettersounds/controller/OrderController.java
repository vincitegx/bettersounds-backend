package com.bettersounds.controller;

import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.services.OrderService;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ){
        Sort.Direction direction = Sort.Direction.ASC;
//        if("name".equals(sortBy.get()) || "genre".equals(sortBy.get())){
//            direction = Sort.Direction.ASC;
//        }else{
//            direction = Sort.Direction.DESC;
//        }
        Page<OrderDto> order = orderService.getAllOrders(
                PageRequest.of(page.orElse(0), 6, direction, sortBy.orElse("id"))
        );
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("orders", order))
                        .message("orders retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
}
