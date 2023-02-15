package com.bettersounds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author TEGA
 */
@Getter
@AllArgsConstructor
public enum SocialProvider {
    
    FACEBOOK("facebook"), TWITTER("twitter"), LINKEDIN("linkedin"), GOOGLE("google"), LOCAL("local");
 
    private final String providerType;
}
