package com.github.secretsanta.service.mapper;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import com.github.secretsanta.web.dto.SecretSantaBody;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SecretSantaMapper {
    SecretSantaMapper INSTANCE = Mappers.getMapper(SecretSantaMapper.class);

    SecretSantaEntity idAndSecretSantaBodytoSecretSantaEntity(Integer id, SecretSantaBody secretSantaBody);
    SecretSantaDTO secretSantaEntityToSecretSantaDTO(SecretSantaEntity secretSantaEntity);
}
