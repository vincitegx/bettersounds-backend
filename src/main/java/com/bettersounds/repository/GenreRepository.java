package com.bettersounds.repository;

import com.bettersounds.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
    Genre findByName(String name);
    
    
}
