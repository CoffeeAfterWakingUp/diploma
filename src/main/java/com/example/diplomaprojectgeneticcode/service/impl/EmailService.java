package com.example.diplomaprojectgeneticcode.service.impl;

import com.example.diplomaprojectgeneticcode.dto.Email;
import com.example.diplomaprojectgeneticcode.service.MailContentBuilder;
import com.example.diplomaprojectgeneticcode.service.interfaces.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import static com.example.diplomaprojectgeneticcode.util.Constant.MESSAGE_ENCODING;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    @Async
    public void send(Email email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MESSAGE_ENCODING);
        String recipient = "";
        try {
            recipient = email.getRecipient();
            helper.setFrom(username);
            helper.setText(mailContentBuilder.build(email.getBody()));
            helper.setTo(recipient);
            helper.setSubject(email.getSubject());
            mailSender.send(mimeMessage);
            log.info("Email is sent to {} !", recipient);
        }catch (MessagingException e) {
            log.error("Failed to send the email", e);
            throw new IllegalStateException("Failed to send email to " + recipient, e);
        }
    }
}
