package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.web.dto.SecretSantaBody;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SecretSantaMapper {
    SecretSantaMapper INSTANCE = Mappers.getMapper(SecretSantaMapper.class);

    @Mapping(target = "giver.participantId", source = "secretSantaBody.giverId")
    @Mapping(target = "receiver.participantId", source = "secretSantaBody.receiverId")
    @Mapping(target="event.eventId", source="secretSantaBody.eventId")
    SecretSantaEntity idAndSecretSantaBodytoSecretSantaEntity(Integer id, SecretSantaBody secretSantaBody);

    @Mapping(target = "giverId", source = "giver.participantId")
    @Mapping(target = "receiverId", source = "receiver.participantId")
    @Mapping(target = "giverName", source = "giver.participantName")
    @Mapping(target = "receiverName", source = "receiver.participantName")
    @Mapping(target="eventId",source="event.eventId")
    SecretSantaDTO secretSantaEntityToSecretSantaDTO(SecretSantaEntity secretSantaEntity);

    List<SecretSantaDTO> secretSantaEntitiesToSecretSantaDTOs(List<SecretSantaEntity> secretSantaEntities);
}
