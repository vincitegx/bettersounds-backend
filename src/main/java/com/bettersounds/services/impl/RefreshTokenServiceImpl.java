package com.bettersounds.services.impl;

import com.bettersounds.domain.RefreshToken;
import com.bettersounds.exception.BetterSoundsException;
import com.bettersounds.repository.RefreshTokenRepository;
import com.bettersounds.services.RefreshTokenService;
import static java.lang.Boolean.TRUE;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService{
    
    private final RefreshTokenRepository refreshTokenRepository;
    
    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BetterSoundsException("Invalid refresh Token"));
    }

    @Override
    public Boolean deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
        return TRUE;
    }
    
}
