package com.estudo.applicationservice.domain.dao;

import com.estudo.applicationservice.domain.models.UserPresence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserPresenceDAO {

    private static String COLLECTION_BASE_NAME = "frequency";
    private final MongoOperations mongoOperations;

    @Autowired
    public UserPresenceDAO (
            final MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public UserPresence insert (final UserPresence userPresence){
        final String PRESENCA_EXISTS = "Presenca já marcada para essa data";

        final Query query = new Query();
        query.addCriteria(Criteria.where("accountId").is(userPresence.getAccountId()).and("data").is(userPresence.getWeekDay()));

        final boolean exists = mongoOperations.exists(query, UserPresence.class);
        if (exists) {
            throw new RuntimeException(PRESENCA_EXISTS);
        }
        return mongoOperations.save(userPresence);
    }
}
