package com.bettersounds.repository;

import com.bettersounds.domain.VerificationToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TEGA
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    
    Optional<VerificationToken> findByToken(String token);
    
}
