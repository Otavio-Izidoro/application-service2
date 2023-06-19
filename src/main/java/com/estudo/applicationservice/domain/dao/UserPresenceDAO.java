package com.estudo.applicationservice.domain.dao;

import com.estudo.applicationservice.domain.models.UserPresence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

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

        final var userPresenceToSave = checkExistentUserPresence(userPresence);
        if(Objects.isNull(userPresenceToSave)) { return null; }

        return mongoOperations.save(userPresenceToSave);
    }

    private UserPresence checkExistentUserPresence (final UserPresence userPresence){
        final Query query = new Query();
        query.addCriteria(Criteria.where("accountId").is(userPresence.getAccountId()).and("weekDay").is(userPresence.getWeekDay()));

        final var userPresenceExists = mongoOperations.find(query, UserPresence.class);

        if (!userPresenceExists.isEmpty()) {
            return checkPresenceField(userPresence, userPresenceExists.get(0));
        }

        return userPresence;
    }

    private UserPresence checkPresenceField (final UserPresence userPresence,
                                             final UserPresence userPresenceExists){

        if(Objects.equals(userPresenceExists.isPresence(), userPresence.isPresence())){
            return null;
        }

        userPresence.setId(userPresenceExists.getId());
        return userPresence;
    }
}
