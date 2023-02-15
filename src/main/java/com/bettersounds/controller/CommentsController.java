package com.bettersounds.controller;

import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.CommentDto;
import com.bettersounds.services.CommentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {
    
//    private final CommentService commentService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAllComments(@PathVariable Long id){
//        List<CommentDto> comments = commentService.getAllCommentsForBeat(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("comments", "comments"))
                        .message("Comments Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
}
