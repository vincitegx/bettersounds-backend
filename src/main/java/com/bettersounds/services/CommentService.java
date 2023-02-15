package com.bettersounds.services;

import com.bettersounds.dto.CommentDto;
import java.util.List;

/**
 *
 * @author TEGA
 */
public interface CommentService {
    
    List<CommentDto> getAllCommentsForBeat(Long id);
    
    List<CommentDto> getAllCommentsForUser(Long id);
    
}
