package com.bettersounds.services;

import com.bettersounds.domain.NotificationEmail;

/**
 *
 * @author TEGA
 */
public interface MailService {
    
    void sendMail(NotificationEmail notificationEmail);
    
}
