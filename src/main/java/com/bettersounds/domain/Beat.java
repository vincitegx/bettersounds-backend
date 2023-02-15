package com.bettersounds.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author TEGA
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Beat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Name cannot be empty or null")
    private String name;

    private double priceMp3;
    
    private double priceWav;

    private double tempo;

    private String uri;

    @OneToOne
    private PurchaseFile purchaseFile;

    private String artWork;

    @Lob
    private String description;

    @OneToOne
    private BeatKey beatKey;

    @OneToOne
    private Mood mood;

//    @NotEmpty(message = "Main Creator cannot be empty or null")
//    private String mainCreator;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<ContributingCreators> contributingCreators;

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    private List<Order> orders;
    
    @OneToOne
    private Genre genre;
    
//    @Builder.Default
//    private Integer orderCount = 0;
    
//    @Column(columnDefinition = "BIT", length = 1,nullable = false)
//    private boolean isFree;
//    
//    @Column(columnDefinition = "BIT", length = 1,nullable = false)
//    private boolean isReleased;
    
//    private Instant releaseDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date postedDate;

//    private Instant modifiedDate;

}
//@Table(name = "beat",
//        uniqueConstraints = {
//            @UniqueConstraint(name = "beat_name",columnNames = "name"),
//            @UniqueConstraint(name = "purchase_file_id",columnNames = "purchase_file_id"),
//            @UniqueConstraint(name = "beat_key_id",columnNames = "beat_key_id"),
//            @UniqueConstraint(name = "mood_id",columnNames = "mood_id")
//        }
//)
