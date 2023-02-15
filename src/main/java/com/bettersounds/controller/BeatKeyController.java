package com.bettersounds.controller;

import com.bettersounds.domain.BeatKey;
import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.BeatKeyDto;
import com.bettersounds.services.BeatKeyService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TEGA
 */
@RestController
@RequestMapping("/api/beatkey")
@RequiredArgsConstructor
@Slf4j
public class BeatKeyController {
    
    private final BeatKeyService beatKeyService;
    
    @PostMapping(path = "/save")
    public ResponseEntity<ApiResponse> saveBeatKey(@Valid @RequestBody BeatKeyDto beatKeyDto) {
        beatKeyService.saveBeatKey(beatKeyDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beatkey", true))
                        .message("Beat Key Saved")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    
    @GetMapping("/allkeys")
    public ResponseEntity<ApiResponse> getAll() {
        List<BeatKey> beatKey = beatKeyService.getAll();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beatKeys", beatKey))
                        .message("BeatKeys Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
}
