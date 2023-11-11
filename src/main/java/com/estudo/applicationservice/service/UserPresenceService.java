package com.estudo.applicationservice.service;

import com.estudo.applicationservice.domain.dao.UserFrequencyDAO;
import com.estudo.applicationservice.domain.dao.UserPresenceDAO;
import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.estudo.applicationservice.helpers.validator.Validate;
import com.estudo.applicationservice.rest.vo.*;
import com.estudo.applicationservice.service.mappers.UserPresenceToUserPresenceResponseMapper;
import com.estudo.applicationservice.service.mappers.UserPresenceRequestToUserPresenceMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Service
public class UserPresenceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPresenceService.class);
    private final UserPresenceDAO userPresenceDAO;
    private final UserFrequencyDAO userFrequencyDAO;
    private final UserFrequencyService userFrequencyService;
    private final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper;
    private final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper;

    public UserPresenceService( final UserPresenceDAO userPresenceDAO,
                                final UserFrequencyDAO userFrequencyDAO,
                                final UserFrequencyService userFrequencyService,
                                final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper,
                                final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper) {

        this.userPresenceDAO = userPresenceDAO;
        this.userFrequencyDAO = userFrequencyDAO;
        this.userFrequencyService = userFrequencyService;
        this.userPresenceRequestToUserPresenceMapper = userPresenceRequestToUserPresenceMapper;
        this.userPresenceToUserPresenceResponseMapper = userPresenceToUserPresenceResponseMapper;
    }

    public UserPresenceResponse updatePresence(final UserPresenceRequest request) {

            final UserPresence userPresenceMap = userPresenceRequestToUserPresenceMapper.map(request);
            final var userPresenceExist = userPresenceDAO.findById(userPresenceMap.getAccountId(),userPresenceMap.getDate());
            final var userFrequency = userFrequencyDAO.findById(userPresenceMap.getAccountId(),userPresenceMap.getDay());
            var validate = new Validate(userPresenceExist.isPresent(),userFrequency.isPresent(),false);

            if(!validFormatDate(userPresenceMap) || !validate.userFrequencyExist()){
                LOGGER.info("Materia nao registrada ou Formato de data errado!");
                return null;
            }

            validate = userFrequencyService.verifyNewCurrentClass(userFrequency.get(), validate, userPresenceMap.isPresence());


            final UserPresence userPresence = userPresenceDAO.update(userPresenceMap);

            if(validate.itsNewClass()){
                return userPresenceToUserPresenceResponseMapper.map(userPresence);
            }
            if(Objects.isNull(userPresence) ||  !userFrequencyService.updateFrequency(userPresence.isPresence(), userFrequency.get())){
                return  null;
            }

            return userPresenceToUserPresenceResponseMapper.map(userPresence);

    }

    public UserPresenceResponse updateContent(final ClassContentRequest request) {

        final UserPresence topic = userPresenceDAO.updateClassContent(request);

        if(Objects.isNull(topic)) {
            return null;
        }
        return userPresenceToUserPresenceResponseMapper.map(topic);
    }

    public UserTopicResponse getTopic (final String accountId, final DayOfWeek day){

        final var userPresences = userPresenceDAO.findTopics(accountId, day);

        if(Objects.isNull(userPresences)){
            return null;
        }

        final List<String> topics = userPresences.stream()
                .map(UserPresence::getTopic)
                .filter(Objects::nonNull)
                .toList();

        return new UserTopicResponse(topics);
    }
    private boolean validFormatDate(final UserPresence userPresence){

        try{
            final LocalDate date = LocalDate.parse(userPresence.getDate());
            userPresence.setDate(date.toString());
        }catch (DateTimeParseException e){
            LOGGER.info("Erro na formatacao da data", e);
            return false;
        }

        return true;
    }
}
