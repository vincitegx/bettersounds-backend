package com.bettersounds.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
public class DefaultBeatKeys {
    
    private final String [] beatkey = {
        "C-Major","C-Minor",
        "C#-Major","C#-Minor",
        "D-Major","D-Minor",
        "D#-Major","D#-Minor",
        "E-Major","E-Minor",
        "F-Major","F-Minor",
        "F#-Major","F#-Minor",
        "G-Major","G-Minor",
        "G#-Major","G#-Minor",    
        "A-Major","A-Minor",
        "A#-Major","A#-Minor",
        "B-Major","B-Minor"
    };
    
}
