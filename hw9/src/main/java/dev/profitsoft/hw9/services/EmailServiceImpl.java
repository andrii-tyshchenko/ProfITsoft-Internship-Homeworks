package dev.profitsoft.hw9.services;

import dev.profitsoft.hw9.data.EmailLetterData;
import dev.profitsoft.hw9.data.EmailLetterStatus;
import dev.profitsoft.hw9.messaging.SendEmailMessage;
import dev.profitsoft.hw9.repositories.EmailLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    private final EmailLetterRepository emailLetterRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, EmailLetterRepository emailLetterRepository) {
        this.emailSender = emailSender;
        this.emailLetterRepository = emailLetterRepository;
    }

    @Override
    public void sendEmailLetter(SendEmailMessage message) {
        String kafkaLetterId = message.getEmailLetterId();

        if (!emailLetterRepository.existsByKafkaLetterId(kafkaLetterId)) {
            EmailLetterData emailLetter = convertToEmailLetterData(message);
            emailLetter.setStatus(EmailLetterStatus.NEW);

            emailLetterRepository.save(emailLetter);

            tryToSendEmailOrChangeStatusIfException(emailLetter);
        }
    }

    @Override
    @Scheduled(fixedDelay = 300_000)
    public void sendUnsentEmailLetters() {
        List<EmailLetterData> unsentEmailLetters = emailLetterRepository.findAllByStatus(EmailLetterStatus.ERROR);

        for (EmailLetterData emailLetter : unsentEmailLetters) {
            tryToSendEmailOrChangeStatusIfException(emailLetter);
        }
    }

    private EmailLetterData convertToEmailLetterData(SendEmailMessage message) {
        EmailLetterData emailLetterData = new EmailLetterData();

        emailLetterData.setEmailAddresses(message.getEmailAddresses());
        emailLetterData.setSubject(message.getSubject());
        emailLetterData.setText(message.getText());
        emailLetterData.setKafkaLetterId(message.getEmailLetterId());

        return emailLetterData;
    }

    private void tryToSendEmailOrChangeStatusIfException(EmailLetterData emailLetter) {
        try {
            for (String toAddress : emailLetter.getEmailAddresses()) {
                sendEmailLetterWithoutAttachments(toAddress, emailLetter.getSubject(), emailLetter.getText());

                Thread.sleep(5000); // Gmail не відсилає повідомлення, якщо їх іде багато одночасно або надто часто
            }

            emailLetter.setStatus(EmailLetterStatus.SENT);
            emailLetter.setErrorMessage(null);

            emailLetterRepository.save(emailLetter);
        } catch (Exception e) {
            emailLetter.setStatus(EmailLetterStatus.ERROR);
            emailLetter.setErrorMessage(e.getClass().getSimpleName() + ": " + e.getMessage());

            emailLetterRepository.save(emailLetter);
        }
    }

    private void sendEmailLetterWithoutAttachments(String toAddress, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}