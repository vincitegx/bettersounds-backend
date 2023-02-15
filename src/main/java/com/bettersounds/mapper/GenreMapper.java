package com.bettersounds.mapper;

import com.bettersounds.domain.Genre;
import com.bettersounds.dto.GenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author TEGA
 */
@Mapper(componentModel = "spring", config = Genre.class)
public interface GenreMapper {
    
    GenreDto mapGenreToDto(Genre genre);
    
    @Mapping(target = "id", ignore = true)
    Genre mapDtoToGenre(GenreDto genreDto);
    
}
