package com.bettersounds.services;

import com.bettersounds.domain.BeatKey;
import com.bettersounds.dto.BeatKeyDto;
import java.util.List;

/**
 *
 * @author TEGA
 */
public interface BeatKeyService {
    
    void saveBeatKey(BeatKeyDto beatKeyDto);
    
    List<BeatKey> getAll();
    
}
