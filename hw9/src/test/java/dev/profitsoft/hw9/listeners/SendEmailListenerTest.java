package dev.profitsoft.hw9.listeners;

import dev.profitsoft.hw9.Hw9Application;
import dev.profitsoft.hw9.config.TestElasticsearchConfiguration;
import dev.profitsoft.hw9.data.EmailLetterData;
import dev.profitsoft.hw9.data.EmailLetterStatus;
import dev.profitsoft.hw9.messaging.SendEmailMessage;
import dev.profitsoft.hw9.repositories.EmailLetterRepository;
import dev.profitsoft.hw9.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext
@ContextConfiguration(classes = {Hw9Application.class, TestElasticsearchConfiguration.class})
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092" })
class SendEmailListenerTest {
    private static final String SUBJECT = "Subject of a test letter";
    private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
    private static final String LETTER_ID = UUID.randomUUID().toString();
    private static final List<String> EMAIL_ADDRESSES = List.of("john.doe@ukr.net");

    @Autowired
    private KafkaOperations<String, SendEmailMessage> kafkaOperations;

    @Autowired
    private EmailLetterRepository emailLetterRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Value("${kafka.topic.sendEmail}")
    private String sendEmailTopic;

    @SpyBean
    private EmailService spyEmailService;

    @MockBean
    private JavaMailSender emailSender;

    @BeforeEach
    public void beforeEach() {
        emailLetterRepository.deleteAll();
    }

    @Test
    void shouldSaveToDBAndChangStatusToSentIfWasSent() throws InterruptedException {
        SendEmailMessage message = new SendEmailMessage();
        message.setSubject(SUBJECT);
        message.setText(TEXT);
        message.setEmailLetterId(LETTER_ID);
        message.setEmailAddresses(EMAIL_ADDRESSES);

        doNothing().when(emailSender).send((SimpleMailMessage) any());

        kafkaOperations.send(sendEmailTopic, message.getSubject(), message);

//        while (!emailLetterRepository.findAll().iterator().hasNext()) {
//            Thread.sleep(50);
//        }
//
//        Iterable<EmailLetterData> letters = emailLetterRepository.findAll();
//
//        Iterator<EmailLetterData> iterator = letters.iterator();
//
//        EmailLetterData letter = iterator.next();

//        assertFalse(iterator.hasNext());
//        assertEquals(SUBJECT, letter.getSubject());
//        assertEquals(TEXT, letter.getText());
//        assertEquals(LETTER_ID, letter.getKafkaLetterId());
//        assertEquals(EMAIL_ADDRESSES, letter.getEmailAddresses());
//        assertEquals(EmailLetterStatus.NEW, letter.getStatus());
//        assertNull(letter.getErrorMessage());

        verify(spyEmailService, after(6_000)).sendEmailLetter(any());
        verify(emailSender).send((SimpleMailMessage) any());

        Iterable<EmailLetterData> letters = emailLetterRepository.findAll();

        Iterator<EmailLetterData> iterator = letters.iterator();

        EmailLetterData letter = iterator.next();

        assertFalse(iterator.hasNext());
        assertEquals(SUBJECT, letter.getSubject());
        assertEquals(TEXT, letter.getText());
        assertEquals(LETTER_ID, letter.getKafkaLetterId());
        assertEquals(EMAIL_ADDRESSES, letter.getEmailAddresses());
        assertEquals(EmailLetterStatus.SENT, letter.getStatus());
        assertNull(letter.getErrorMessage());
    }

    @Test
    void shouldSaveToDBAndChangStatusToErrorIfUnsent() throws InterruptedException {
        SendEmailMessage message = new SendEmailMessage();
        message.setSubject(SUBJECT);
        message.setText(TEXT);
        message.setEmailLetterId(LETTER_ID);
        message.setEmailAddresses(EMAIL_ADDRESSES);

        doThrow(new MailSendException("Error sending email")).when(emailSender).send((SimpleMailMessage) any());

        kafkaOperations.send(sendEmailTopic, message.getSubject(), message);

//        while (!emailLetterRepository.findAll().iterator().hasNext()) {
//            Thread.sleep(50);
//        }
//
//        Iterable<EmailLetterData> letters = emailLetterRepository.findAll();
//
//        Iterator<EmailLetterData> iterator = letters.iterator();
//
//        EmailLetterData letter = iterator.next();

//        assertFalse(iterator.hasNext());
//        assertEquals(SUBJECT, letter.getSubject());
//        assertEquals(TEXT, letter.getText());
//        assertEquals(LETTER_ID, letter.getKafkaLetterId());
//        assertEquals(EMAIL_ADDRESSES, letter.getEmailAddresses());
//        assertEquals(EmailLetterStatus.NEW, letter.getStatus());
//        assertNull(letter.getErrorMessage());

        verify(spyEmailService, after(6_000)).sendEmailLetter(any());
        verify(emailSender).send((SimpleMailMessage) any());

        Iterable<EmailLetterData> letters = emailLetterRepository.findAll();

        Iterator<EmailLetterData> iterator = letters.iterator();

        EmailLetterData letter = iterator.next();

        assertFalse(iterator.hasNext());
        assertEquals(SUBJECT, letter.getSubject());
        assertEquals(TEXT, letter.getText());
        assertEquals(LETTER_ID, letter.getKafkaLetterId());
        assertEquals(EMAIL_ADDRESSES, letter.getEmailAddresses());
        assertEquals(EmailLetterStatus.ERROR, letter.getStatus());
        assertEquals("MailSendException: Error sending email", letter.getErrorMessage());
    }
}