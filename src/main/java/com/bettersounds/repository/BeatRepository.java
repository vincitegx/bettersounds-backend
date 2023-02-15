package com.bettersounds.repository;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.Genre;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface BeatRepository extends JpaRepository<Beat, Long>{
    
    @Query("select b from Beat b where b.name like %?1%")
    Page<Beat> findByName(String name,Pageable pageable);
    
    @Query("select b from Beat b inner join Genre g on (b.genre.id = g.id) where b.name like %?1% or g.name like %?1%")
    Page<Beat> findByNameContaining(String name,Pageable pageable);
    
//    @Query(value = "SELECT * FROM `beat` INNER JOIN beat_genre_set ON (beat.id = beat_genre_set.beat_id) INNER JOIN genre ON (beat_genre_set.genre_id = genre.genreid) WHERE beat.name LIKE '%?1%' OR genre.genrename LIKE '%?2%'", nativeQuery = true)
//    Page<Beat> findByName(String name,String genre, Pageable pageable);
    
    @Query(value="select * from beat where beat.name like %?1%", nativeQuery = true)
    Page<Beat> getMainCreatorByName(String name,Pageable pageable);
    
//    @Query("select b from Beat b inner join beat_genre_set bgs on (b.id = bgs.beat_id) inner join genre g on (bgs.genre_id = g.id) where b.name like %?1% or g.name like %?1%")
//    Page<Beat> findByNameOrGenre(String name,Genre g, Pageable pageable);
    
//    Optional<List<Beat>> findByName(String name);
    Optional<Beat> findByName(String name);
    Optional<Slice<Beat>> findByGenre(String genre, Sort sort);
    Optional<List<Beat>> findByPriceMp3(String priceMp3, Sort sort);
 
    @Query("select b from Beat b where (b.priceMp3 <= 0) and (b.priceWav <= 0) and ( b.name like %?1%)")
    Page<Beat> findByNameWherePriceMp3AndPriceWavLessThanOrEqualsToZero(String name,Double price, Pageable pageable);
}
