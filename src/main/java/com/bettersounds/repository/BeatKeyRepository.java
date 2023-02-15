package com.bettersounds.repository;

import com.bettersounds.domain.BeatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface BeatKeyRepository extends JpaRepository<BeatKey, Long>{
    
}
