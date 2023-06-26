package com.estudo.applicationservice.service;

import com.estudo.applicationservice.domain.dao.UserFrequencyDAO;
import com.estudo.applicationservice.domain.dao.UserPresenceDAO;
import com.estudo.applicationservice.domain.models.UserFrequency;
import com.estudo.applicationservice.domain.models.UserPresence;
import org.springframework.stereotype.Service;

@Service
public class UserFrequencyService {

    private final UserFrequencyDAO userFrequencyDAO;
    private final UserPresenceDAO userPresenceDAO;


    public UserFrequencyService( final UserFrequencyDAO userFrequencyDAO,
                                 final UserPresenceDAO userPresenceDAO) {

        this.userFrequencyDAO = userFrequencyDAO;
        this.userPresenceDAO= userPresenceDAO;
    }

    public boolean verifyNewCurrentClass(final UserPresence userPresence){
        final var userExists = userPresenceDAO.findById(userPresence.getAccountId(),userPresence.getDate());

        if(userExists.isEmpty()){
            final var userFrequency = userFrequencyDAO.findById(userPresence.getAccountId(),userPresence.getDay());
            if(userFrequency.isPresent()){
                addNewCurrentClass(userFrequency.get(), userPresence.isPresence());
                return true;
            }
        }
        return false;
    }

    public boolean updateFrequency (final UserPresence userPresence,
                                    final boolean itsNewClass) {

        final var oldFrequency = userFrequencyDAO.findById(userPresence.getAccountId(), userPresence.getDay());

        if(oldFrequency.isEmpty()){
            System.out.println("Materia nao registrada!");
            return false;
        }
        final var frequencyToSave = oldFrequency.get();

        if(itsNewClass){
            return updateNewFrequency(frequencyToSave);
        }


        final UserFrequency invertedFrequency;
        if(userPresence.isPresence()){
            invertedFrequency = invertPresenceAndAbsence(frequencyToSave, 1, -1);
        }else{
            invertedFrequency = invertPresenceAndAbsence(frequencyToSave, -1, 1);
        }
        return updateNewFrequency(invertedFrequency);

    }


    private void addNewCurrentClass(
            final UserFrequency userFrequency,
            final boolean isPresence){

        final var updatedFrequency = updateNewPresenceOrAbsence(isPresence,userFrequency);
        userFrequencyDAO.updateFrequency(updatedFrequency);
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
