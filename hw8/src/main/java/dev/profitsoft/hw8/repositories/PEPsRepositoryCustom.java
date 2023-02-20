package dev.profitsoft.hw8.repositories;

import com.mongodb.BasicDBObject;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;

import java.util.List;

public interface PEPsRepositoryCustom {
    /**
     * Inserts {@code BasicDBObject} to a MongoDB database.
     * @param basicDBObject object, which should be inserted to a database
     */
//    void insertBasicDBObject(BasicDBObject basicDBObject);
    void insertBasicDBObject(BasicDBObject basicDBObject);

    /**
     * Returns list of {@code PEPsTopFirstNamesDto} objects that represent N first most popular PEPs' first names
     * and total count of their appearance in a database in a descendant order (from the most popular to the least popular).
     * PEP should have "isPEP" field value equal "true" to be counted.
     * N is represented by given "limit" argument.
     * @param limit number of first most popular names to be shown
     * @return most popular first names of PEPs and count of their appearance in a database in a descendant order (by count)
     */
    List<PEPsTopFirstNamesDto> getTopFirstNamesAndTheirQuantityWhereIsPepTrue(int limit);
}