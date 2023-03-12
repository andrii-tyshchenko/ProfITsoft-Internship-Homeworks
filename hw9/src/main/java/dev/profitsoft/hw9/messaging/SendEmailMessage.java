package dev.profitsoft.hw9.messaging;

import java.util.List;

public class SendEmailMessage {
    private String emailLetterId;

    private List<String> emailAddresses;

    private String subject;

    private String text;

    public String getEmailLetterId() {
        return emailLetterId;
    }

    public void setEmailLetterId(String emailLetterId) {
        this.emailLetterId = emailLetterId;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}