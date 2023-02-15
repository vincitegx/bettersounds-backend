package com.bettersounds.services;

import com.bettersounds.domain.Genre;
import com.bettersounds.dto.GenreDto;
import java.util.List;

/**
 *
 * @author TEGA
 */
public interface GenreService {
 
    void saveGenre(GenreDto genreDto);
    
    List<Genre> getAll();
}
