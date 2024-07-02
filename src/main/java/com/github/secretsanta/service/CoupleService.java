package com.github.secretsanta.service;

import com.github.secretsanta.repository.couple.CoupleRepository;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.mapper.CoupleMapper;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.dto.CoupleBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoupleService {
    private final CoupleRepository coupleRepository;
    private final ParticipantRepository participantRepository;
    public Integer addCouple(CoupleBody coupleBody) {
        Optional<CoupleEntity> existingCouple = coupleRepository.findByParticipant1_ParticipantIdAndParticipant2_ParticipantId(coupleBody.getParticipant1Id(), coupleBody.getParticipant2Id());
        if(!existingCouple.isPresent()){
            existingCouple = coupleRepository.findByParticipant2_ParticipantIdAndParticipant1_ParticipantId(coupleBody.getParticipant2Id(),coupleBody.getParticipant1Id());
        }
        if(existingCouple.isPresent()){
            throw new NotAcceptException("Couple already exists");
        }

        CoupleEntity coupleEntity = CoupleMapper.INSTANCE.idAndCoupleBodyToCoupleEntity(null, coupleBody);
        CoupleEntity coupleCreated = coupleRepository.save(coupleEntity);

        return coupleCreated.getCoupleId();
    }

    public List<CoupleEntity> getAllCouples() {
       return coupleRepository.findAll();
    }
}
