package com.estudo.applicationservice.service;

import com.estudo.applicationservice.domain.dao.UserPresenceDAO;
import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceResponse;
import com.estudo.applicationservice.service.mappers.UserPresenceToUserPresenceResponseMapper;
import com.estudo.applicationservice.service.mappers.UserPresenceRequestToUserPresenceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserPresenceService {

    private final UserPresenceDAO userPresenceDAO;
    private final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper;
    private final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper;


    public UserPresenceService( final UserPresenceDAO userPresenceDAO,
                                final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper,
                                final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper) {

        this.userPresenceDAO = userPresenceDAO;
        this.userPresenceRequestToUserPresenceMapper = userPresenceRequestToUserPresenceMapper;
        this.userPresenceToUserPresenceResponseMapper = userPresenceToUserPresenceResponseMapper;
    }
    final private String SUCCESS = "Presenca atualizada com sucesso!!";

    final private String FAILED = "Houve um erro ao atualizar a presenca";


    public UserPresenceResponse atualizarPresenca(final UserPresenceRequest request) {

            final UserPresence userPresenceMap = userPresenceRequestToUserPresenceMapper.map(request);
            final UserPresence userPresence = userPresenceDAO.insert(userPresenceMap);


            return userPresenceToUserPresenceResponseMapper.map(userPresence);

    }
}
