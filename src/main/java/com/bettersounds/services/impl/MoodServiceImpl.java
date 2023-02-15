package com.bettersounds.services.impl;

import com.bettersounds.domain.Mood;
import com.bettersounds.dto.MoodDto;
import com.bettersounds.mapper.MoodMapper;
import com.bettersounds.repository.MoodRepository;
import com.bettersounds.services.MoodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@RequiredArgsConstructor
public class MoodServiceImpl implements MoodService{
    
    private final MoodRepository moodRepository;
//    private final MoodMapper moodMapper;

    @Override
    public void saveMood(MoodDto moodDto) {
        moodRepository.save(mapDtoToMood(moodDto));
    }

    @Override
    public List<Mood> getAll() {
        List<Mood> mood = moodRepository.findAll();
        return mood;
    }

    public MoodDto mapMoodToDto(Mood mood) {
        return MoodDto.builder()
                .id(mood.getId())
                .name(mood.getName())
                .build();
    }

    public Mood mapDtoToMood(MoodDto moodDto) {
        return Mood.builder()
                .id(moodDto.getId())
                .name(moodDto.getName())
                .build();
    }
    
}
