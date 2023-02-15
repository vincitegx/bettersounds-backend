package com.bettersounds.controller;

import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.JwtAuthResponse;
import com.bettersounds.dto.LoginRequest;
import com.bettersounds.dto.RefreshTokenRequest;
import com.bettersounds.dto.SignupRequest;
import com.bettersounds.services.AuthService;
import java.time.LocalDateTime;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest registerRequest) {
        authService.registerNewUser(registerRequest);
        return new ResponseEntity<>("User Registration Successful",OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully",OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("JwtResponse", authService.login(loginRequest)))
                        .message("Login Successful !!!")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
    @PostMapping("/refresh/token")
    public JwtAuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("taskStatus", authService.logout(refreshTokenRequest)))
                        .message("Logout Successful !!!")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
