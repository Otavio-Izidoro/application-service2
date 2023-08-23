package com.estudo.applicationservice.domain.dao;

import com.estudo.applicationservice.domain.models.UserFrequency;
import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserFrequencyDAO extends BaseDAO{

    private final MongoOperations mongoOperations;

    @Autowired
    public UserFrequencyDAO (
            final MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public Optional<UserFrequency> findById(final String accountId, final DayOfWeek day){
        final var query = buildQueryByAccountIdAndDay(accountId, day);
        return Optional.ofNullable( mongoOperations.findOne(query, UserFrequency.class));
    }
    public void updateFrequency(final UserFrequency userFrequency){
        mongoOperations.save(userFrequency);
    }

    public boolean updateNewFrequency(final UserFrequency userFrequency){
        final var query = buildQueryByAccountIdAndDay(userFrequency.getAccountId(), userFrequency.getDay());
        final var update = new Update();
        update.set("frequency", userFrequency.getFrequency());
        update.set("presences", userFrequency.getPresences());
        update.set("absences", userFrequency.getAbsences());

        final var wasUpdated = mongoOperations.updateFirst(query, update, UserFrequency.class);
        return isUpdateResultMatchedCountValid(wasUpdated);
    }

    private Query buildQueryByAccountIdAndDay(final String accountId, final DayOfWeek day){
        final Query query = new Query();
        return query.addCriteria(Criteria.where("accountId").is(accountId).and("day").is(day));
    }
}
