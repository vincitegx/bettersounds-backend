package com.bettersounds.controller;

import com.bettersounds.domain.Mood;
import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.GenreDto;
import com.bettersounds.dto.MoodDto;
import com.bettersounds.services.GenreService;
import com.bettersounds.services.MoodService;
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
@RequestMapping("/api/mood")
@RequiredArgsConstructor
@Slf4j
public class MoodController {

    private final MoodService moodService;
    
    @PostMapping(path = "/save")
    public ResponseEntity<ApiResponse> saveMood(@Valid @RequestBody MoodDto moodDto) {
        moodService.saveMood(moodDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Mood", true))
                        .message("Mood Saved")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    
    @GetMapping("/allmoods")
    public ResponseEntity<ApiResponse> getAllBeatMoods() {
        List<Mood> mood = moodService.getAll();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("moods", mood))
                        .message("Moods Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
