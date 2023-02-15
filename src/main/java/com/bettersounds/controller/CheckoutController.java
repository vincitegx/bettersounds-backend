package com.bettersounds.controller;

import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.services.CheckoutService;
import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author TEGA
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checkout")
public class CheckoutController {
    
    private final CheckoutService checkoutService;
    
    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse> placeOrder(@Valid @RequestBody OrderDto orderDto) {
        boolean orderTransactionSuccessful = checkoutService.placeOrder(orderDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("taskStatus", orderTransactionSuccessful))
                        .message("Order Transaction Successful")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }    
}
