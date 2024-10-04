package com.github.secretsanta.service;

import com.github.secretsanta.repository.couple.CoupleRepository;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.repository.event.EventRepository;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.exceptions.InvalidValueException;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.CoupleMapper;
import com.github.secretsanta.service.mapper.EventMapper;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.dto.CoupleBody;
import com.github.secretsanta.web.dto.CoupleDTO;
import com.github.secretsanta.web.dto.ParticipantDTO;
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

        if(coupleBody.getParticipant1Id()==null||coupleBody.getParticipant2Id()==null){
           throw new NotAcceptException("입력값을 찾을 수 없습니다. 커플 정보를 다시 입력하세요!");
        }
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

    public String deleteCouple(int coupleId) {
        CoupleEntity existingCouple = coupleRepository.findById(coupleId)
                .orElseThrow(()-> new NotAcceptException("Couple doesn't exist"));
       coupleRepository.deleteById(coupleId);
       return "커플 id:" + existingCouple.getCoupleId() + ", 이름1:" + existingCouple.getParticipant1().getParticipantName() + ", 이름2:"
               + existingCouple.getParticipant2().getParticipantName()+" 님이 커플 정보에서 삭제되었습니다.";
    }


    public List<CoupleDTO> getAllCouplesByEvent(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(()->new NotFoundException("해당 Id:"+eventId+"를 찾을 수 없습니다."));
        List<CoupleEntity> coupleEntities = coupleRepository.findAllByEvent(eventEntity);
        List<CoupleDTO> coupleDTOs = CoupleMapper.INSTANCE.coupleEntitiesToCoupleDTOs(coupleEntities);

        return coupleDTOs;
    }


    //이것도 정상출력 가능함. 대신 이벤트 번호가 출력이 안됨.
    public List<CoupleDTO> getAllCouples() {
        List<CoupleEntity> coupleEntities = coupleRepository.findAll();
        List<CoupleDTO> coupleDTOs = CoupleMapper.INSTANCE.coupleEntitiesToCoupleDTOs(coupleEntities);
        return coupleDTOs;
    }


}
