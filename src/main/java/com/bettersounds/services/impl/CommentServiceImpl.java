package com.bettersounds.services.impl;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.CommentDto;
import com.bettersounds.exception.BetterSoundsException;
import com.bettersounds.mapper.CommentMapper;
import com.bettersounds.repository.BeatRepository;
//import com.bettersounds.repository.CommentRepository;
import com.bettersounds.repository.UserRepository;
import com.bettersounds.services.CommentService;
import java.util.List;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
//@Service
//@RequiredArgsConstructor
//public class CommentServiceImpl implements CommentService{
//    
//    private final CommentRepository commentRepository;
//    private final BeatRepository beatRepository;
//    private final CommentMapper commentMapper;
//    private final UserRepository userRepository;
//    
//    @Override
//    public List<CommentDto> getAllCommentsForBeat(Long id) {
//        Beat beat = beatRepository.findById(id).orElseThrow(() -> new BetterSoundsException(id.toString()));
//        return commentRepository.findByBeat(beat)
//                .stream()
//                .map(commentMapper::mapToDto).collect(toList());
//    }
//    
//    @Override
//    public List<CommentDto> getAllCommentsForUser(Long id) {
//        Users user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));
//        return commentRepository.findAllByUser(user)
//                .stream()
//                .map(commentMapper::mapToDto)
//                .collect(toList());
//    }
//}
