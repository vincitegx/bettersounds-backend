package com.bettersounds.controller;

import com.bettersounds.dto.JwtAuthResponse;
import com.bettersounds.dto.LoginRequest;
import com.bettersounds.dto.OrderDto;
import com.bettersounds.services.PaypalService;
import com.paypal.base.rest.PayPalRESTException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/paypal")
@AllArgsConstructor
@Slf4j
public class PayPalController {
    
    private final PaypalService paypalService;
    
    @PostMapping("/pay")
    public ResponseEntity<Boolean> payment(@RequestBody OrderDto orderDto){
        boolean payed = false;
//        try {
//            paypalService.createPayment(orderDto.getPayment().getPrice(), "USD", "paypal", "", "Buy Beat", "", "");
//            payed = true;
//        } catch (PayPalRESTException ex) {
//            Logger.getLogger(PayPalController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return ResponseEntity.ok(payed);
    }
    
}
