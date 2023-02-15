package com.bettersounds.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TEGA
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private BeatDto beatDto;
    private Instant createdDate;
    private String text;
    private UserInfo userInfo;
}
