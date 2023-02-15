package com.bettersounds.services.impl;

import com.bettersounds.domain.BeatKey;
import com.bettersounds.dto.BeatKeyDto;
import com.bettersounds.mapper.BeatKeyMapper;
import com.bettersounds.repository.BeatKeyRepository;
import com.bettersounds.services.BeatKeyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@RequiredArgsConstructor
public class BeatKeyServiceImpl implements BeatKeyService{
    
    private final BeatKeyRepository beatKeyRepository;
//    private final BeatKeyMapper beatKeyMapper;

    @Override
    public void saveBeatKey(BeatKeyDto beatKeyDto) {
        beatKeyRepository.save(mapDtoToBeatKey(beatKeyDto));
    }

    @Override
    public List<BeatKey> getAll() {
        List<BeatKey> beatKey = beatKeyRepository.findAll();
        return beatKey;
    }
    
    private BeatKey mapDtoToBeatKey(BeatKeyDto beatKeyDto){
        return BeatKey.builder()
                .id(beatKeyDto.getId())
                .name(beatKeyDto.getName())
                .build();
    }
    
    
}
