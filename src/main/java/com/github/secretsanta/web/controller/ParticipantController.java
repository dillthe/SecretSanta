package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.service.ParticipantService;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/event/{eventId}")
public class ParticipantController {
    private final ParticipantService participantService;

    @Operation(summary="참가자 정보 입력")
    @PostMapping("/participants")
    public String createParticipant(@Valid @PathVariable int eventId, @Valid @RequestBody ParticipantBody participantBody){
        participantBody.setEventId(eventId);
            int participantId = participantService.createParticipant(participantBody);
//        ParticipantEntity createdParticipant= participantService.createParticipant(participantBody);
            return "참가자 번호: "+ participantId +", 이름: "+participantBody.getParticipantName()+"님이 이벤트번호: " + eventId  +"에 저장되었습니다.";
    }
    @Operation(summary="참가자 정보 삭제")
    @DeleteMapping("/participants")
    public String deleteParticipant(@Valid @PathVariable int eventId, @Valid @RequestBody ParticipantDTO participantDTO){
        participantDTO.setEventId(eventId);
        int participantId = participantService.deleteParticiant(participantDTO);
        return "이벤트번호: " + eventId+"에서 참가자 "+ participantId +"번, 이메일 : "+participantDTO.getEmail()+", " +
                "이름 : "+participantDTO.getParticipantName()+ "님이 삭제되었습니다.";
    }

    @Transactional
    @Operation(summary="참가자 정보 수정")
    @PutMapping("/participants/{participantId}")
    public String updateParticipant(@Valid @PathVariable int eventId,
                                    @Valid @PathVariable("participantId") Integer participantId,
                                    @Valid @RequestBody ParticipantBody participantBody){
        participantBody.setEventId(eventId);
        participantService.updateParticipant(participantId,participantBody);
        return "참가자:"+ participantId +"의 정보가 수정되었습니다";
    }

//    @Operation(summary="참가자 리스트 조회")
//    @GetMapping("/all")
//    public List<ParticipantEntity> getAllParticipants(){
//        List<ParticipantEntity> participants = participantService.getAllParticipants();
//        return participants;
//    }

    @Operation(summary="참가자 리스트 조회")
    @GetMapping("/participants")
    public List<ParticipantDTO> getAllParticipants(@Valid @PathVariable int eventId){
        List<ParticipantDTO> participantDTOs = participantService.getAllParticipantsByEventId(eventId);
        return participantDTOs;
    }

//    가족 참가자 수 정의
//            가족 참가자 수 수정

}

