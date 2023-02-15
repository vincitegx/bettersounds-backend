package com.bettersounds.controller;

import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class AdminDashboardController {
    
    @GetMapping("/order/total/today")
    public Integer orderTotalToday(){
        return 12;
    }
    
    @GetMapping("/visitors/total/today")
    public Integer visitorsTotalToday(){
        return 12;
    }
    
    @GetMapping("/sales/total/today")
    public Integer salesTotalToday(){
        return 12;
    }
    
    @GetMapping("/comments/total/today")
    public Integer commentsTotalToday(@PathVariable String token){
        return 12;
    }
}
