package com.estudo.applicationservice.domain.dao;

import com.mongodb.client.result.UpdateResult;
import io.micrometer.common.util.StringUtils;

public abstract class BaseDAO {


    protected boolean isUpdateResultValid (final UpdateResult updateResult){
        return isUpdateResultValid(updateResult, false);
    }
    protected boolean isUpdateResultMatchedCountValid(final UpdateResult updateResult) {

        return isUpdateResultValid(updateResult, true);
    }

    protected boolean isUpdateResultValid(
            final UpdateResult updateResult, final boolean matchedCriteria) {

        if (updateResult == null) {
            return false;
        }
        if (updateResult.getUpsertedId() == null) {
            return matchedCriteria
                    ? updateResult.getModifiedCount() > 0 || updateResult.getMatchedCount() > 0
                    : updateResult.getModifiedCount() > 0;
        } else {
            if (updateResult.getUpsertedId().isString()) {
                return StringUtils.isNotEmpty(updateResult.getUpsertedId().asString().getValue());
            }
            final var entries =
                    updateResult.getUpsertedId().asDocument().entrySet().stream()
                            .toList();
            for (final var entry : entries) {
                if (!StringUtils.isNotEmpty(entry.getValue().toString())) {
                    return false;
                }
            }
            return true;
        }
    }

}
