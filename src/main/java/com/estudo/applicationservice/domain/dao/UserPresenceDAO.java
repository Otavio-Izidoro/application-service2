package com.estudo.applicationservice.domain.dao;

import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.rest.vo.ClassContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class UserPresenceDAO extends BaseDAO{

    private static String COLLECTION_BASE_NAME = "frequency";
    private final MongoOperations mongoOperations;

    @Autowired
    public UserPresenceDAO (
            final MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public UserPresence update(final UserPresence userPresence){

        final var userPresenceToSave = checkExistentUserPresence(userPresence);
        if(Objects.isNull(userPresenceToSave)) { return null; }

        return mongoOperations.save(userPresenceToSave);
    }

    public UserPresence updateClassContent(final ClassContentRequest request){
        final var userPresence = findById(request.getAccountId(),request.getDate());

        if(userPresence.isEmpty()) { return null; }

        userPresence.get().setClassContent(request.getContent());


        return mongoOperations.save(userPresence.get());
    }

    public Optional<UserPresence> findById(final String accountId, final String date){
        final var query = buildQueryByAccountIdAndDate(accountId,date);
        return findByQuery(query);
    }

    private Optional<UserPresence> findByQuery(final Query query){

        return Optional.ofNullable( mongoOperations.findOne(query, UserPresence.class));
    }

    private Query buildQueryByAccountIdAndDate(final String accountId, final String date){
        final Query query = new Query();
        return query.addCriteria(Criteria.where("accountId").is(accountId).and("date").is(date));
    }

    private UserPresence checkExistentUserPresence (final UserPresence userPresence){
        final var userPresenceExists = findById(userPresence.getAccountId(),userPresence.getDate());

        if (userPresenceExists.isPresent()) {
            return checkPresenceField(userPresence, userPresenceExists.get());
        }

        return userPresence;
    }

    private UserPresence checkPresenceField (final UserPresence userPresence,
                                             final UserPresence userPresenceExists){

        if(Objects.equals(userPresenceExists.isPresence(), userPresence.isPresence())){
            return null;
        }

        userPresenceExists.setPresence(userPresence.isPresence());
        return userPresenceExists;
    }
}
