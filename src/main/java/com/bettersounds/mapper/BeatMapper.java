package com.bettersounds.mapper;

import com.bettersounds.domain.Beat;
import com.bettersounds.dto.BeatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Beat.class)
public interface BeatMapper {

    BeatDto mapToBeatDto(Beat beat);
    
    @Mapping(target = "postedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", source = "beatDto.id")
    @Mapping(target = "name", source = "beatDto.name")
    Beat mapToBeat(BeatDto beatDto);
}
