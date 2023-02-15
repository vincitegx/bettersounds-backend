package com.bettersounds.mapper;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.Comment;
import com.bettersounds.domain.Users;
import com.bettersounds.dto.BeatDto;
import com.bettersounds.dto.CommentDto;
import com.bettersounds.dto.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Comment.class)
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "beat", expression = "java(mapBeatDtoToBeat(commentDto.getBeatDto()))")
    @Mapping(target = "user", expression = "java(mapUserInfoToUser(commentDto.getUserInfo()))")
    Comment map(CommentDto commentDto);

    CommentDto mapToDto(Comment comment);
    
    
    Users mapUserInfoToUser(UserInfo userInfo);
    
    Beat mapBeatDtoToBeat(BeatDto beatDto);
}
