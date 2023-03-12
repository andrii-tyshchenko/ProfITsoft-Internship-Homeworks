package dev.profitsoft.hw9.services;

import dev.profitsoft.hw9.messaging.SendEmailMessage;

public interface EmailService {
    void sendEmailLetter(SendEmailMessage message);

    void sendUnsentEmailLetters();
}