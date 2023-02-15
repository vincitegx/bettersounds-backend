package com.bettersounds.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
public class DefaultOperations {
    
    private final String [] operations = {"CREATE","READ","UPDATE","DELETE"};      
}
