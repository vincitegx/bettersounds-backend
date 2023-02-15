package com.bettersounds.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author TEGA
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BetterSoundsException extends RuntimeException{

    public BetterSoundsException(String message) {
        super(message);
    }
    
    public BetterSoundsException(String message, Throwable e) {
        super(message, e);
    }
}
