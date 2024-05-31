package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantMapper {
    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(target="participantId", source = "id")
     ParticipantEntity idAndParticipantBodytoParticipantEntity(Integer id, ParticipantBody participantBody);


    ParticipantDTO participantEntityToParticipantDTO(ParticipantEntity participantEntity);
}
