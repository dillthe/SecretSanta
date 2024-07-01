package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.web.dto.CoupleBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoupleMapper {
    CoupleMapper INSTANCE = Mappers.getMapper(CoupleMapper.class);

    @Mapping(target="participant1.participantId", source = "coupleBody.participant1Id")
    @Mapping(target="participant2.participantId", source = "coupleBody.participant2Id")
     CoupleEntity idAndCoupleBodyToCoupleEntity(Integer id, CoupleBody coupleBody);


//    @Mapping(target="couple", source = "coupleBody.getParticipant2Id")
//    CoupleDTO participantEntityToParticipantDTO(ParticipantEntity participantEntity);
}
