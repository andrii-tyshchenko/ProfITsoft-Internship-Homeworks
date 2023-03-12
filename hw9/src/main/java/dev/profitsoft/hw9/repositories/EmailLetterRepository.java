package dev.profitsoft.hw9.repositories;

import dev.profitsoft.hw9.data.EmailLetterData;
import dev.profitsoft.hw9.data.EmailLetterStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailLetterRepository extends CrudRepository<EmailLetterData, String> {
    List<EmailLetterData> findAllByStatus(EmailLetterStatus emailLetterStatus);

    boolean existsByKafkaLetterId(String kafkaLetterId);
}