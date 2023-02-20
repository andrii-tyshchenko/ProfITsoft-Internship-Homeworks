package dev.profitsoft.hw8.repositories;

import com.mongodb.BasicDBObject;
import dev.profitsoft.hw8.dtos.PEPsTopFirstNamesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Repository
public class PEPsRepositoryCustomImpl implements PEPsRepositoryCustom {
    private static final String PEPS_COLLECTION_NAME = "peps";

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PEPsRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insertBasicDBObject(BasicDBObject basicDBObject) {
        mongoTemplate.insert(basicDBObject, PEPS_COLLECTION_NAME);
    }

    @Override
    public List<PEPsTopFirstNamesDto> getTopFirstNamesAndTheirQuantityWhereIsPepTrue(int limit) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("is_pep").is(true)),
                group("first_name").count().as("quantity"),
                sort(Sort.by(Sort.Direction.DESC, "quantity")),
                limit(limit)
        );

        AggregationResults<PEPsTopFirstNamesDto> result = mongoTemplate.aggregate(
                aggregation, PEPS_COLLECTION_NAME, PEPsTopFirstNamesDto.class
        );

        return result.getMappedResults();
    }
}