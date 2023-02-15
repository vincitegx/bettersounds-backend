package com.bettersounds.services;

import com.bettersounds.dto.BeatDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TEGA
 */
public interface BeatService {
    
    BeatDto uploadBeat(BeatDto beatDto, MultipartFile artWork,MultipartFile taggedmp3,MultipartFile untaggedmp3,MultipartFile untaggewav);
    BeatDto editBeat(BeatDto beatDto, MultipartFile artWork,MultipartFile taggedmp3,MultipartFile untaggedmp3,MultipartFile untaggedwav);
    
    boolean deleteBeat(Long id);
    
    Page<BeatDto> getAllBeats(String name, PageRequest pageRequest);
    
    BeatDto getBeat(Long id);
    
    BeatDto getBeatByName(String name);
    
    BeatDto getJson(String beatDto);

    Page<BeatDto> getAllFreeBeats(String orElse, PageRequest pageRequest);

    List<BeatDto> getBeats(List<String> id);
}
