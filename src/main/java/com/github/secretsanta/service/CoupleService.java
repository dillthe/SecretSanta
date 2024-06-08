//package com.github.secretsanta.service;
//
//import com.github.secretsanta.repository.couple.CoupleRepository;
//import com.github.secretsanta.repository.entity.CoupleEntity;
//import com.github.secretsanta.repository.entity.ParticipantEntity;
//import com.github.secretsanta.repository.participant.ParticipantRepository;
//import com.github.secretsanta.service.exceptions.NotAcceptException;
//import com.github.secretsanta.service.mapper.CoupleMapper;
//import com.github.secretsanta.service.mapper.ParticipantMapper;
//import com.github.secretsanta.web.dto.CoupleBody;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CoupleService {
//    private final CoupleRepository coupleRepository;
//    private final ParticipantRepository participantRepository;
//    public Integer addCouple(CoupleBody coupleBody) {
//        boolean coupleExists = coupleRepository.findByParticipants(coupleBody.getParticipant1Id(), coupleBody.getParticipant2Id()).isPresent();
//        if(coupleExists){
//            throw new NotAcceptException("해당 커플이 이미 존재합니다.");
//        }
//        ParticipantEntity participant1 = participantRepository.findById(coupleBody.getParticipant1Id())
//                .orElseThrow(() -> new IllegalArgumentException("참가자 1을 찾을 수 없습니다."));
//        ParticipantEntity participant2 = participantRepository.findById(coupleBody.getParticipant2Id())
//                .orElseThrow(() -> new IllegalArgumentException("참가자 2를 찾을 수 없습니다."));
//
//        CoupleEntity coupleEntity = CoupleMapper.INSTANCE.idAndCoupleBodyToCoupleEntity(null, coupleBody);
//        CoupleEntity coupleCreated = coupleRepository.save(coupleEntity);
//
//        return coupleCreated.getCoupleId();
//    }
//}
