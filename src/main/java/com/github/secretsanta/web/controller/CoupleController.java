package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.service.CoupleService;
import com.github.secretsanta.web.dto.CoupleBody;
import com.github.secretsanta.web.dto.CoupleDTO;
import com.github.secretsanta.web.dto.ParticipantBody;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/event/{eventId}")
public class CoupleController {
    private final CoupleService coupleService;
    @Operation(summary="커플 정보 입력")
    @PostMapping("/couple")
    public String addCouple(@Valid @PathVariable int eventId, @Valid @RequestBody CoupleBody coupleBody){
        coupleBody.setEventId(eventId);
        Integer coupleId = coupleService.addCouple(coupleBody);
        return "커플Id:"+coupleId +":"+ coupleBody.getParticipant1Id()+"님과 "+coupleBody.getParticipant2Id()+"님의 커플 정보 입력이 완료되었습니다.";
    }

    @Operation(summary="커플 정보 삭제")
    @DeleteMapping("/couple/{coupleId}")
    public String deleteCouple(@Valid @PathVariable int eventId, @Valid @PathVariable int coupleId){
        String deletion = coupleService.deleteCouple(coupleId);
        return deletion;
    }

    @Operation(summary="커플 정보 확인")
    @GetMapping("/couples")
    public List<CoupleDTO> getAllCouples(@Valid @PathVariable int eventId){
        return coupleService.getAllCouplesByEvent(eventId);
    }

    @Operation(summary="전 이벤트 커플 정보")
    @GetMapping("/allcouples")
    public List<CoupleDTO> getAllCouples(){
        return coupleService.getAllCouples();
    }
}
