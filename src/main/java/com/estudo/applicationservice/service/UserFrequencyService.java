package com.estudo.applicationservice.service;

import com.estudo.applicationservice.controller.UserPresenceController;
import com.estudo.applicationservice.domain.dao.UserFrequencyDAO;
import com.estudo.applicationservice.domain.dao.UserPresenceDAO;
import com.estudo.applicationservice.domain.models.UserFrequency;
import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.estudo.applicationservice.helpers.validator.Validate;
import com.estudo.applicationservice.rest.vo.UserFrequencyRequest;
import com.estudo.applicationservice.rest.vo.UserFrequencyResponse;
import com.estudo.applicationservice.service.mappers.UserFrequencyRequestToUserFrequencyMapper;
import com.estudo.applicationservice.service.mappers.UserFrequencyToUserFrequencyResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserFrequencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFrequencyService.class);
    private final UserFrequencyDAO userFrequencyDAO;
    private final UserPresenceDAO userPresenceDAO;
    private final UserFrequencyToUserFrequencyResponseMapper userFrequencyToUserFrequencyResponseMapper;

    private final UserFrequencyRequestToUserFrequencyMapper userFrequencyRequestToUserFrequencyMapper;
    public UserFrequencyService( final UserFrequencyDAO userFrequencyDAO,
                                 final UserPresenceDAO userPresenceDAO,
                                 final UserFrequencyToUserFrequencyResponseMapper userFrequencyToUserFrequencyResponseMapper,
                                 final UserFrequencyRequestToUserFrequencyMapper userFrequencyRequestToUserFrequencyMapper) {

        this.userFrequencyDAO = userFrequencyDAO;
        this.userPresenceDAO= userPresenceDAO;
        this.userFrequencyToUserFrequencyResponseMapper = userFrequencyToUserFrequencyResponseMapper;
        this.userFrequencyRequestToUserFrequencyMapper = userFrequencyRequestToUserFrequencyMapper;
    }

    public Validate verifyNewCurrentClass(final UserFrequency userFrequency, final Validate validate,
                                          final boolean isPresence){



        if(!validate.userPresenceExist() && validate.userFrequencyExist()){
            validate.setItsNewClass(true);
            addNewCurrentClass(userFrequency, isPresence);
            updateNewFrequency(userFrequency);
        }

        return validate;
    }

    public boolean updateFrequency (final boolean isPresence, UserFrequency userFrequency) {

        if(isPresence){
            invertPresenceAndAbsence(userFrequency, 1, -1);
        }else{
            invertPresenceAndAbsence(userFrequency, -1, 1);
        }
        return updateNewFrequency(userFrequency);

    }

    public UserFrequencyResponse getFrequencyByAccountId (final String accountId, final DayOfWeek day){

        final var userFrequency = userFrequencyDAO.findById(accountId, day);

        return userFrequency.map(userFrequencyToUserFrequencyResponseMapper::map).orElse(null);
    }

    public UserFrequencyResponse updateUserInformation (final UserFrequencyRequest request){
        if(!StringUtils.hasText(request.getAccountId())){
            return null;
        }
        final UserFrequency userFrequencyMap = userFrequencyRequestToUserFrequencyMapper.map(request);
        userFrequencyMap.setFrequency(0.00);
        userFrequencyMap.setNumberCurrentClasses(0);
        userFrequencyMap.setPresences(0);
        userFrequencyMap.setAbsences(0);
       final UserFrequency userFrequency = userFrequencyDAO.update(userFrequencyMap);
       if(Objects.isNull(userFrequency)){
           return null;
       }
        return userFrequencyToUserFrequencyResponseMapper.map(userFrequency);
    }

    private void addNewCurrentClass(
            final UserFrequency userFrequency,
            final boolean isPresence){

        final var updatedFrequency = updateNewPresenceOrAbsence(isPresence,userFrequency);
        userFrequencyDAO.updateNewFrequency(updatedFrequency);
    }

    private boolean updateNewFrequency(final UserFrequency userFrequency){
        final var newFrequency = calculatePresencePercentage(userFrequency.getPresences(),userFrequency.getNumberCurrentClasses());
        userFrequency.setFrequency(newFrequency);
        return userFrequencyDAO.updateNewFrequency(userFrequency);
    }

    private UserFrequency updateNewPresenceOrAbsence(
            final boolean isPresence,
            final UserFrequency userFrequency){

        userFrequency.addNumberCurrentClasses(userFrequency.getNumberCurrentClasses(), 1);

        if(isPresence){
            userFrequency.setPresences(userFrequency.getPresences()+1);
        }else {
            userFrequency.setAbsences(userFrequency.getAbsences()+1);
        }
        return userFrequency;
    }

    private UserFrequency invertPresenceAndAbsence(
            final UserFrequency userFrequency,
            final Integer presenceValue,
            final Integer absenceValue){

        userFrequency.setPresences(sumValues(userFrequency.getPresences(),presenceValue));
        userFrequency.setAbsences(sumValues(userFrequency.getAbsences(),absenceValue));
        return userFrequency;
    }
    private Integer sumValues(
            final Integer value1,
            final Integer value2){
        return value1+value2;
    }

    private Double calculatePresencePercentage (
            final Integer presences,
            final Integer numberCurrentClasses){

        return ((double) presences/(double) numberCurrentClasses*100.00);
    }
}
