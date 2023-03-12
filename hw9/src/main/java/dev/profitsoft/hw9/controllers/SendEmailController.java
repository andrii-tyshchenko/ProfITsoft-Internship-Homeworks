package dev.profitsoft.hw9.controllers;

import dev.profitsoft.hw9.dtos.SendEmailDto;
import dev.profitsoft.hw9.messaging.SendEmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/send_email")
public class SendEmailController {
    private final KafkaOperations<String, SendEmailMessage> kafkaOperations;

    @Value("${kafka.topic.sendEmail}")
    private String sendEmailTopic;

    @Autowired
    public SendEmailController(KafkaOperations<String, SendEmailMessage> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @PostMapping
    public void sendEmail(@RequestBody SendEmailDto dto) {
        SendEmailMessage message = convertToSendEmailMessage(dto);

        kafkaOperations.send(sendEmailTopic, dto.getSubject(), message);
    }

    private SendEmailMessage convertToSendEmailMessage(SendEmailDto dto) {
        SendEmailMessage message = new SendEmailMessage();

        message.setEmailLetterId(UUID.randomUUID().toString());
        message.setEmailAddresses(dto.getEmailAddresses());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        return message;
    }
}