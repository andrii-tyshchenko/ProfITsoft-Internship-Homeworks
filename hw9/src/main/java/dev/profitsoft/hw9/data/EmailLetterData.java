package dev.profitsoft.hw9.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName="email_letters")
public class EmailLetterData {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private List<String> emailAddresses;

    @Field(type = FieldType.Text)
    private String subject;

    @Field(type = FieldType.Text)
    private String text;

    private EmailLetterStatus status;

    @Field(type = FieldType.Keyword)
    private String kafkaLetterId;

    @Field(type = FieldType.Text)
    private String errorMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public EmailLetterStatus getStatus() {
        return status;
    }

    public void setStatus(EmailLetterStatus status) {
        this.status = status;
    }

    public String getKafkaLetterId() {
        return kafkaLetterId;
    }

    public void setKafkaLetterId(String kafkaLetterId) {
        this.kafkaLetterId = kafkaLetterId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}