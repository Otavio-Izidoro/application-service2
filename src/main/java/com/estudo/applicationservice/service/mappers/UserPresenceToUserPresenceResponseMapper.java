package com.estudo.applicationservice.service.mappers;

import com.estudo.applicationservice.domain.models.UserPresence;
import com.estudo.applicationservice.rest.vo.UserPresenceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserPresenceToUserPresenceResponseMapper {

    UserPresenceResponse map (final UserPresence userPresence);

}
