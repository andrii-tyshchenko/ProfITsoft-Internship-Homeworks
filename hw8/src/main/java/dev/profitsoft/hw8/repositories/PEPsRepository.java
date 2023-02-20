package dev.profitsoft.hw8.repositories;

import dev.profitsoft.hw8.data.PEPData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PEPsRepository extends MongoRepository<PEPData, String>, PEPsRepositoryCustom {
    /**
     * Returns list of {@code PEPData} objects from a MongoDB database, that match given String arguments.
     * These arguments represent first name, patronymic and last name.
     * @param firstName first name of a PEP in Ukrainian
     * @param patronymic patronymic of a PEP in Ukrainian
     * @param lastName last name of a PEP in Ukrainian
     * @return list of {@code PEPData} objects from a database that have the same parts of a name as given arguments
     */
    List<PEPData> findByFirstNameUAAndPatronymicAndLastNameUA(String firstName, String patronymic, String lastName);
}