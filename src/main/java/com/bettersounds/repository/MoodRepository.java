package com.bettersounds.repository;

import com.bettersounds.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface MoodRepository extends JpaRepository<Mood, Long>{
    
}
