package com.bettersounds.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author TEGA
 */
@Data
@AllArgsConstructor
public class DefaultGenres {

    private final String [] genre = {
        "Afro-Pop","Hip-Pop","Pop","R&B","Blues","Reggae","World",
        "Others","House","Trap","Country","Metal","Gospel","Dance",
        "Soul","Electronic","Rock","Soul","Rap"};
    
}
