package com.github.secretsanta.service;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.controller.ParticipantController;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private static final Logger logger = LoggerFactory.getLogger(ParticipantController.class);

    public Integer createParticipant(ParticipantBody participantBody) {
        boolean emailExists = participantRepository.findByEmail(participantBody.getEmail()).isPresent();
        if(emailExists){
            throw new NotAcceptException("이메일 " + participantBody.getEmail() + "이(가) 이미 존재합니다.");
        }
        ParticipantEntity participantEntity = ParticipantMapper.INSTANCE.idAndParticipantBodytoParticipantEntity(null,participantBody);
        ParticipantEntity participantCreated =participantRepository.save(participantEntity);
        return participantCreated.getParticipantId();
    }

    public Integer deleteParticiant(ParticipantDTO participantDTO) {
        Integer participantId = participantDTO.getParticipantId();
        participantRepository.deleteById(participantId);
        return participantId;
    }


    @Transactional
    public Integer updateParticipant(Integer participantId, ParticipantBody participantBody) {
        logger.info("Received request to update participant with ID: {}", participantId);
        logger.info("Request body: {}", participantBody);

        ParticipantEntity participantEntityUpdated = participantRepository.findById(participantId)
                 .orElseThrow(()->new NotFoundException("해당 Id:"+participantId+"를 찾을 수 없습니다."));
        participantEntityUpdated.setParticipantId(participantId);
        participantEntityUpdated.setParticipantBody(participantBody);
        participantRepository.save(participantEntityUpdated);
         return participantEntityUpdated.getParticipantId();
    }


    public List<ParticipantEntity> getAllParticipants() {
        return participantRepository.findAll();
    }


}
