package com.bettersounds.services.impl;

import com.bettersounds.domain.NotificationEmail;
import com.bettersounds.exception.MailServiceException;
import com.bettersounds.services.MailContentBuilder;
import com.bettersounds.services.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author TEGA
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private MailContentBuilder mailContentBuilder;
    
    @Value("${company.properties.mail}")
    private String email;
    
    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(email);
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new MailServiceException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
        
    }    
}
