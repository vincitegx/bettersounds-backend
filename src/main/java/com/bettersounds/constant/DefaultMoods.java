package com.bettersounds.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
public class DefaultMoods {
    
    private final String [] mood = {
        "None","Dark","Energy","Light","Sad","Happy"
    };
    
}
