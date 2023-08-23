package com.estudo.applicationservice.service.mappers;

import com.estudo.applicationservice.domain.models.UserFrequency;
import com.estudo.applicationservice.rest.vo.UserFrequencyResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserFrequencyToUserFrequencyResponseMapper {

    UserFrequencyResponse map (final UserFrequency userPresence);
}
