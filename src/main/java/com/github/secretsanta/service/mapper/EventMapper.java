package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.web.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target="eventName", source = "eventBody.eventName")
    @Mapping(target="eventDescription", source = "eventBody.eventDescription")
    @Mapping(target="eventDate", source = "eventBody.eventDate")
     EventEntity idAndEventBodyToEventEntity(Integer id, EventBody eventBody);


    @Mapping(target="eventId", source = "eventId")
    @Mapping(target="eventName", source = "eventName")
    @Mapping(target="eventDescription", source = "eventDescription")
    @Mapping(target="eventDate", source = "eventDate")
    @Mapping(target="participants", source="participants")
    @Mapping(target = "couples", source = "couples")
    @Mapping(target = "secretSantas", source = "secretSantas")
    EventDTO eventEntityToEventDTO(EventEntity eventEntity);

    @Mapping(target="eventId", source="event.eventId")
    ParticipantDTO participantEntityToParticipantDTO(ParticipantEntity participantEntity);


    @Mapping(target = "participant1Id", source = "participant1.participantId")
    @Mapping(target = "participant2Id", source = "participant2.participantId")
    @Mapping(target= "eventId", source="event.eventId")
    CoupleDTO coupleEntityToCoupleDTO(CoupleEntity coupleEntity);

    List<CoupleDTO> coupleEntitiesToCoupleDTOs(List<CoupleEntity> coupleEntities);

    @Mapping(target = "giverId", source = "giver.participantId")
    @Mapping(target = "receiverId", source = "receiver.participantId")
    SecretSantaDTO secretSantaEntityToSecretSantaDTO(SecretSantaEntity secretSantaEntity);

    List<SecretSantaDTO> secretSantaEntitiesToSecretSantaDTOs(List<SecretSantaEntity> secretSantaEntities);

    List<EventDTO> eventEntitiesToEventDTOs(List<EventEntity> eventEntities);
}
