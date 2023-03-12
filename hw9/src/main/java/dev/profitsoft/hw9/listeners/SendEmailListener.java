package dev.profitsoft.hw9.listeners;

import dev.profitsoft.hw9.messaging.SendEmailMessage;
import dev.profitsoft.hw9.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SendEmailListener {
    private final EmailService emailService;

    @Autowired
    public SendEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${kafka.topic.sendEmail}")
    public void sendEmail(SendEmailMessage message) {
        emailService.sendEmailLetter(message);
    }
}