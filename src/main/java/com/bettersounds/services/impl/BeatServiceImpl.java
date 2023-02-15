package com.bettersounds.services.impl;

import com.bettersounds.domain.Beat;
import com.bettersounds.domain.PurchaseFile;
import com.bettersounds.dto.BeatDto;
import com.bettersounds.exception.BetterSoundsException;
import com.bettersounds.repository.BeatRepository;
import com.bettersounds.repository.PurchaseFileRepository;
import com.bettersounds.services.BeatService;
import com.bettersounds.services.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author TEGA
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BeatServiceImpl implements BeatService {

    private final BeatRepository beatRepository;
    private final FileStorageService fileStorageService;
    private final PurchaseFileRepository purchasFileRepository;

    @Override
    public BeatDto uploadBeat(BeatDto beatDto, MultipartFile artWork, MultipartFile taggedmp3, MultipartFile untaggedmp3, MultipartFile untaggedwav) {
        PurchaseFile purchaseFile = new PurchaseFile();
        if (beatRepository.findByName(beatDto.getName()).isPresent()) {
            throw new BetterSoundsException("Beat with name " + beatDto.getName() + " already exist");
        } else {
            String artWorkUrl = fileStorageService.uploadFile(artWork);
            String untaggedmp3Url = fileStorageService.uploadFile(untaggedmp3);
            String taggedmp3Url = fileStorageService.uploadFile(taggedmp3);
            String untaggedWavUrl = fileStorageService.uploadFile(untaggedwav);
            purchaseFile.setMp3Uri(untaggedmp3Url);
            purchaseFile.setWavUri(untaggedWavUrl);
            purchaseFile = purchasFileRepository.save(purchaseFile);
            beatDto.setArtWork(artWorkUrl);
            beatDto.setUri(taggedmp3Url);
            beatDto.setPurchaseFile(purchaseFile);
            beatDto.setPostedDate(Date.from(Instant.now()));
            Beat beat = beatRepository.save(mapToBeat(beatDto));
            beatDto = mapToBeatDto(beat);
        }
        return beatDto;
    }

    @Override
    public BeatDto editBeat(BeatDto beatDto, MultipartFile artWork, MultipartFile taggedmp3, MultipartFile untaggedmp3, MultipartFile untaggedwav) {
        PurchaseFile purchaseFile = new PurchaseFile();
        if (getBeat(beatDto.getId()) == null) {
            throw new BetterSoundsException("No Beat with name " + beatDto.getName() + " exist");
        } else {
            String artWorkUrl = StringUtils.cleanPath(artWork.getOriginalFilename());
            artWorkUrl = artWorkUrl.replace(' ', '_');
            String untaggedmp3Url = StringUtils.cleanPath(untaggedmp3.getOriginalFilename());
            untaggedmp3Url = untaggedmp3Url.replace(' ', '_');
            String taggedmp3Url = StringUtils.cleanPath(taggedmp3.getOriginalFilename());
            taggedmp3Url = taggedmp3Url.replace(' ', '_');
            String untaggedWavUrl = StringUtils.cleanPath(untaggedwav.getOriginalFilename());
            untaggedWavUrl = untaggedWavUrl.replace(' ', '_');
            beatDto.setName(beatDto.getName().replace(' ', '_'));
            artWorkUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/beat/download/").path(beatDto.getName() + "/").path(artWorkUrl).toUriString();
            untaggedmp3Url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/beat/download/").path(beatDto.getName() + "/").path(untaggedmp3Url).toUriString();
            taggedmp3Url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/beat/download/").path(beatDto.getName() + "/").path(taggedmp3Url).toUriString();
            untaggedWavUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/beat/download/").path(beatDto.getName() + "/").path(untaggedWavUrl).toUriString();
            fileStorageService.uploadFile(artWork);
            fileStorageService.uploadFile(untaggedmp3);
            fileStorageService.uploadFile(taggedmp3);
            fileStorageService.uploadFile(untaggedwav);
            purchaseFile.setMp3Uri(untaggedmp3Url);
            purchaseFile.setWavUri(untaggedWavUrl);
            purchaseFile = purchasFileRepository.save(purchaseFile);
            beatDto.setArtWork(artWorkUrl);
            beatDto.setUri(taggedmp3Url);
            beatDto.setPurchaseFile(purchaseFile);
            Beat beat = beatRepository.save(mapToBeat(beatDto));
            beatDto = mapToBeatDto(beat);
        }
        return beatDto;
    }

    @Override
    public boolean deleteBeat(Long id) {
        beatRepository.deleteById(id);
        return TRUE;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BeatDto> getAllBeats(String name, PageRequest pageRequest) {
        Page<Beat> beat = beatRepository.findByNameContaining(name, pageRequest);
        Page<BeatDto> beatDto = beat.map(this::mapToBeatDto);
        return beatDto;
    }

    @Override
    public Page<BeatDto> getAllFreeBeats(String name, PageRequest pageRequest) {
        double price = 0.0;
        Page<Beat> beat = beatRepository.findByNameWherePriceMp3AndPriceWavLessThanOrEqualsToZero(name, price, pageRequest);
        Page<BeatDto> beatDto = beat.map(this::mapToBeatDto);
        return beatDto;
    }

    @Override
    public BeatDto getBeat(Long id) {
        return mapToBeatDto(beatRepository.findById(id).get());
    }

    @Override
    public List<BeatDto> getBeats(List<String> id) {
        try{
            List<BeatDto> beatDtos = new ArrayList<>();
            id.forEach((i)->{
                Long ids = Long.parseLong(i);
                BeatDto beatDto = mapToBeatDto(beatRepository.findById(ids).orElseThrow(()-> new BetterSoundsException("No Such Beat Found")));
                beatDtos.add(beatDto);
            });
            return beatDtos;
        }catch(BetterSoundsException ex){
            throw new BetterSoundsException(ex.getMessage());
        }
    }
    
    @Override
    public BeatDto getBeatByName(String name) {
        return mapToBeatDto(beatRepository.findByName(name).get());
    }

    @Override
    public BeatDto getJson(String beatDto) {
        BeatDto beatJson = new BeatDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            beatJson = objectMapper.readValue(beatDto, BeatDto.class);
        } catch (IOException ex) {
            Logger.getLogger(BeatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beatJson;
    }

    public BeatDto mapToBeatDto(Beat beat) {
        return BeatDto.builder()
                .artWork(beat.getArtWork())
                .beatKey(beat.getBeatKey())
                .description(beat.getDescription())
                .genre(beat.getGenre())
                .mood(beat.getMood())
                .id(beat.getId())
                .name(beat.getName())
                .priceMp3(beat.getPriceMp3())
                .priceWav(beat.getPriceWav())
                .purchaseFile(beat.getPurchaseFile())
                .postedDate(beat.getPostedDate())
                .tempo(beat.getTempo())
                .uri(beat.getUri())
                .build();
    }

    public Beat mapToBeat(BeatDto beatDto) {
        return Beat.builder()
                .artWork(beatDto.getArtWork())
                .beatKey(beatDto.getBeatKey())
                .description(beatDto.getDescription())
                .genre(beatDto.getGenre())
                .mood(beatDto.getMood())
                .id(beatDto.getId())
                .name(beatDto.getName())
                .priceMp3(beatDto.getPriceMp3())
                .priceWav(beatDto.getPriceWav())
                .purchaseFile(beatDto.getPurchaseFile())
                .tempo(beatDto.getTempo())
                .uri(beatDto.getUri())
                .postedDate(beatDto.getPostedDate())
                .build();
    }       
}
