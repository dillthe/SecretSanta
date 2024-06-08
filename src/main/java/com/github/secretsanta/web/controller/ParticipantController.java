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
@RequestMapping("/api/participant")
public class ParticipantController {
    private final ParticipantService participantService;

    @Operation(summary="참가자 정보 입력")
    @PostMapping("/create")
    public ResponseEntity<String> createParticipant(@Valid @RequestBody ParticipantBody participantBody){
            Integer participantId = participantService.createParticipant(participantBody);
//        ParticipantEntity createdParticipant= participantService.createParticipant(participantBody);
            return ResponseEntity.ok("참가자 "+ participantId +" 번, 이름 : "+participantBody.getParticipantName()+"님이 저장되었습니다.");
    }
    @Operation(summary="참가자 정보 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteParticipant(@Valid @RequestBody ParticipantDTO participantDTO){
        Integer participantId = participantService.deleteParticiant(participantDTO);
        return ResponseEntity.ok("참가자 "+ participantId +" 번, 이름 : "+participantDTO.getParticipantName()
                +"님, 이메일 : "+participantDTO.getEmail()+"이 삭제되었습니다.");
    }

    @Transactional
    @Operation(summary="참가자 정보 수정")
    @PutMapping("/update/{participantId}")
    public ResponseEntity<String> updateParticipant(@Valid @PathVariable("participantId") Integer participantId,
                                                    @Valid @RequestBody ParticipantBody participantBody){
            participantService.updateParticipant(participantId,participantBody);
        return ResponseEntity.ok("참가자:"+ participantId +"의 정보가 수정되었습니다");
    }

    @Operation(summary="참가자 리스트 조회")
    @GetMapping("/all")
    public ResponseEntity<List<ParticipantEntity>> getAllParticipants(){
        List<ParticipantEntity> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }

//    가족 참가자 수 정의
//            가족 참가자 수 수정

}

