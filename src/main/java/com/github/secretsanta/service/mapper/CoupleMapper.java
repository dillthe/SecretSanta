package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.web.dto.CoupleBody;
import com.github.secretsanta.web.dto.CoupleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoupleMapper {
    CoupleMapper INSTANCE = Mappers.getMapper(CoupleMapper.class);

    @Mapping(target="participant1.participantId", source = "coupleBody.participant1Id")
    @Mapping(target="participant2.participantId", source = "coupleBody.participant2Id")
    @Mapping(target="event.eventId", source="coupleBody.eventId")
    CoupleEntity idAndCoupleBodyToCoupleEntity(Integer id, CoupleBody coupleBody);
    @Mapping(target = "eventId", source = "event.eventId") // Map eventId from EventEntity
    @Mapping(target = "participant1Id", source = "participant1.participantId")
    @Mapping(target = "participant2Id", source = "participant2.participantId")
    @Mapping(target = "participant1Name", source = "participant1.participantName")
    @Mapping(target = "participant2Name", source = "participant2.participantName")
    CoupleDTO coupleEntityToCoupleDTO(CoupleEntity coupleEntity);
     List<CoupleDTO> coupleEntitiesToCoupleDTOs(List<CoupleEntity> coupleEntities);


}
