package com.bettersounds.services;

import com.bettersounds.domain.Mood;
import com.bettersounds.dto.MoodDto;
import java.util.List;

/**
 *
 * @author TEGA
 */
public interface MoodService {

    void saveMood(MoodDto moodDto);
    
    List<Mood> getAll();
}
