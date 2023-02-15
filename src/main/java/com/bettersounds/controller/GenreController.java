package com.bettersounds.controller;

import com.bettersounds.domain.Genre;
import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.BeatKeyDto;
import com.bettersounds.dto.GenreDto;
import com.bettersounds.services.BeatKeyService;
import com.bettersounds.services.GenreService;
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
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreController {
    
    private final GenreService genreService;
    
    @PostMapping(path = "/save")
    public ResponseEntity<ApiResponse> saveGenre(@Valid @RequestBody GenreDto genreDto) {
        genreService.saveGenre(genreDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Genre", true))
                        .message("Genre Saved")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    
    @GetMapping("/allgenres")
    public ResponseEntity<ApiResponse> getAllBeatGenres() {
        List<Genre> genre = genreService.getAll();
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("genres", genre))
                        .message("Genres Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
}
