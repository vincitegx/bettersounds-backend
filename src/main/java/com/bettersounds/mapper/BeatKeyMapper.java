package com.bettersounds.mapper;

import com.bettersounds.domain.BeatKey;
import com.bettersounds.dto.BeatKeyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = BeatKey.class, uses = {})
public interface BeatKeyMapper {
    
    BeatKeyDto mapBeatKeyToDto(BeatKey beatKey);
    
    @Mapping(target = "id", ignore = true)
    BeatKey mapDtoToBeatKey(BeatKeyDto beatKeyDto);
    
}
