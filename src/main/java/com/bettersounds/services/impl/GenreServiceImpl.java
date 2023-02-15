package com.bettersounds.services.impl;

import com.bettersounds.domain.Genre;
import com.bettersounds.dto.GenreDto;
import com.bettersounds.mapper.GenreMapper;
import com.bettersounds.repository.GenreRepository;
import com.bettersounds.services.GenreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
//    private final GenreMapper genreMapper;
    
    @Override
    public void saveGenre(GenreDto genreDto) {
        genreRepository.save(mapDtoToGenre(genreDto));
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genre = genreRepository.findAll();
        return genre;
    }

    public GenreDto mapGenreToDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    public Genre mapDtoToGenre(GenreDto genreDto) {
        return Genre.builder()
                .id(genreDto.getId())
                .name(genreDto.getName())
                .build();
    }
    
}
