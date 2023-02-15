package com.bettersounds.startup;

import com.bettersounds.constant.DefaultGenres;
import com.bettersounds.domain.Genre;
import com.bettersounds.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author TEGA
 */
@Component
@Order(3)
public class GenreAddition implements CommandLineRunner{

    @Autowired
    private GenreRepository genreRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
//        DefaultGenres defaultGenres =  new DefaultGenres();
//        for(String genre: defaultGenres.getGenre()){
//            
//            Genre genres = new Genre();
//            genres.setName(genre);
//            genreRepository.save(genres);
//        }
        
    }
    
}
