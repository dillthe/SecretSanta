package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.service.ParticipantService;
import com.github.secretsanta.web.dto.ParticipantBody;
import com.github.secretsanta.web.dto.ParticipantDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

