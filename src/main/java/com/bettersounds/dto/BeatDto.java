package com.bettersounds.dto;

import com.bettersounds.domain.BeatKey;
import com.bettersounds.domain.Genre;
import com.bettersounds.domain.Mood;
import com.bettersounds.domain.PurchaseFile;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeatDto{
    
    private Long id;
    
    private String name;
    
    private double priceMp3;
    
    private double priceWav;
    
    private double tempo;
    
    private Genre genre;
    
    private String uri;
    
    private String artWork;
    
    private String description;
    
    private BeatKey beatKey;
    
    private Mood mood;
    
    private PurchaseFile purchaseFile;
    
//    private String mainCreator;
    
//    private Set<ContributingCreators> contributingCreators;
    
//    private List<Order> orders;
    
//    private Integer orderCount;
    
//    private boolean releaseStatus;
//    
//    private Instant releaseDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected Date postedDate;
    
//    private Instant modifiedDate;
    
//    public BeatDto(Long id,String name, double priceMp3,double priceWav, double tempo,
//            Genre genre, String uri,String artWork,String description,
//            BeatKey beatKey, Mood mood,String mainCreator,Set<ContributingCreators> contributingCreators, 
//            Instant postedDate, Instant modifiedDate) {
//        this.id = id;
//        this.name = name;
//        this.priceMp3 = priceMp3;
//        this.priceWav = priceWav;
//        this.tempo = tempo;
//        this.genre = genre;
//        this.uri = uri;
//        this.artWork = artWork;
//        this.description = description;
//        this.beatKey = beatKey;
//        this.mood = mood;
//        this.mainCreator = mainCreator;
//        this.contributingCreators = contributingCreators;
//        this.postedDate = postedDate;
//        this.modifiedDate = modifiedDate;
//    }
}
