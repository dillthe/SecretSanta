package com.github.secretsanta.service;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.dto.ParticipantBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public Integer createParticipant(ParticipantBody participantBody) {
        boolean emailExists = participantRepository.findByEmail(participantBody.getEmail()).isPresent();
        if(emailExists){
            throw new NotAcceptException("이메일 " + participantBody.getEmail() + "이(가) 이미 존재합니다.");
        }
        ParticipantEntity participantEntity = ParticipantMapper.INSTANCE.idAndParticipantBodytoParticipantEntity(null,participantBody);
        ParticipantEntity participantCreated =participantRepository.save(participantEntity);
        return participantCreated.getParticipantId();
    }
}
