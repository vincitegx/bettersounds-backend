package com.bettersounds.mapper;

import com.bettersounds.domain.Mood;
import com.bettersounds.dto.MoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Mood.class)
public interface MoodMapper {
    
    MoodDto mapMoodToDto(Mood mood);
    
    @Mapping(target = "id", ignore = true)
    Mood mapDtoToMood(MoodDto moodDto);
    
}
