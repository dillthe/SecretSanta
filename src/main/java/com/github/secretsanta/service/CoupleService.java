package com.github.secretsanta.service;

import com.github.secretsanta.repository.couple.CoupleRepository;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.repository.event.EventRepository;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.CoupleMapper;
import com.github.secretsanta.service.mapper.EventMapper;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.dto.CoupleBody;
import com.github.secretsanta.web.dto.CoupleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoupleService {
    private final CoupleRepository coupleRepository;
    private final EventRepository eventRepository;

    private final ParticipantRepository participantRepository;
    private CoupleMapper coupleMapper;
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

        CoupleDTO coupleDTO = CoupleMapper.INSTANCE.coupleEntityToCoupleDTO(coupleCreated);

        return coupleDTO.getCoupleId();
    }


//이것도 정상출력 가능함. 대신 이벤트 번호가 출력이 안됨.
//    public List<CoupleEntity> getAllCouples() {
//        return coupleRepository.findAll();
//    }



    public List<CoupleDTO> getAllCouplesByEvent(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(()->new NotFoundException("해당 Id:"+eventId+"를 찾을 수 없습니다."));
        List<CoupleEntity> coupleEntities = coupleRepository.findAllByEvent(eventEntity);
        List<CoupleDTO> coupleDTOs = CoupleMapper.INSTANCE.coupleEntitiesToCoupleDTOs(coupleEntities);

        return coupleDTOs;
    }

}
