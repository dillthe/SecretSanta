package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.web.dto.CoupleDTO;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ParticipantMapper {
    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(target="event.eventId", source="participantBody.eventId")
    ParticipantEntity idAndParticipantBodytoParticipantEntity(Integer id, ParticipantBody participantBody);

    @Mapping(target="eventId",source="event.eventId")
    ParticipantDTO participantEntityToParticipantDTO(ParticipantEntity participantEntity);

    List<ParticipantDTO> participantEntitiesToParticipantDTOs(List<ParticipantEntity> participantEntities);
}
