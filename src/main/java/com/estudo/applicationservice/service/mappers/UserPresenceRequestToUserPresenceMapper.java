package com.estudo.applicationservice.service.mappers;

import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import org.mapstruct.Mapper;

@Mapper
public interface UserPresenceRequestToUserPresenceMapper {

    UserPresence map(final UserPresenceRequest userPresenceRequest);

}
