package com.bettersounds.dto;

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
public class MoodDto {
    
    private Long id;
    
    private String name;
    
}
