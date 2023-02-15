/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettersounds.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */

@RestController
public class HomeController {
    
    @GetMapping("/login")
    public String home(){
        //Testing oauth2 process
        return "<a href='/oauth2/authorization/google'>login with google</a>";
    }
    
}
