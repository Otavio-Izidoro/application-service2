package com.estudo.applicationservice.service;

import com.estudo.applicationservice.domain.dao.UserPresenceDAO;
import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.rest.vo.ClassContentRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceResponse;
import com.estudo.applicationservice.service.mappers.UserPresenceToUserPresenceResponseMapper;
import com.estudo.applicationservice.service.mappers.UserPresenceRequestToUserPresenceMapper;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserPresenceService {

    private final UserPresenceDAO userPresenceDAO;
    private final UserFrequencyService userFrequencyService;
    private final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper;
    private final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper;

    public UserPresenceService( final UserPresenceDAO userPresenceDAO,
                                final UserFrequencyService userFrequencyService,
                                final UserPresenceRequestToUserPresenceMapper userPresenceRequestToUserPresenceMapper,
                                final UserPresenceToUserPresenceResponseMapper userPresenceToUserPresenceResponseMapper) {

        this.userPresenceDAO = userPresenceDAO;
        this.userFrequencyService = userFrequencyService;
        this.userPresenceRequestToUserPresenceMapper = userPresenceRequestToUserPresenceMapper;
        this.userPresenceToUserPresenceResponseMapper = userPresenceToUserPresenceResponseMapper;
    }

    public UserPresenceResponse updatePresence(final UserPresenceRequest request) {

            final UserPresence userPresenceMap = userPresenceRequestToUserPresenceMapper.map(request);

            final var itsNewClass = userFrequencyService.verifyNewCurrentClass(userPresenceMap);

        System.out.println(itsNewClass+" its new class");
            final UserPresence userPresence = userPresenceDAO.update(userPresenceMap);

        System.out.println(userPresence);

            if(Objects.isNull(userPresence) ||  !userFrequencyService.updateFrequency(userPresence, itsNewClass)){
                return  null;
            }

            return userPresenceToUserPresenceResponseMapper.map(userPresence);

    }

    public UserPresenceResponse updateContent(final ClassContentRequest request) {

        final UserPresence classContent = userPresenceDAO.updateClassContent(request);

        if(Objects.isNull(classContent)) {
            return null;
        }
        return userPresenceToUserPresenceResponseMapper.map(classContent);
    }

}
