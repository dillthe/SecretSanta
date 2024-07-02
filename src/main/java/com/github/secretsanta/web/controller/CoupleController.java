package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.service.CoupleService;
import com.github.secretsanta.web.dto.CoupleBody;
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
@RequestMapping("/api/couple")
public class CoupleController {
    private final CoupleService coupleService;
    @Operation(summary="커플 정보 입력")
    @PostMapping("/add")
    public String addCouple(@Valid @RequestBody CoupleBody coupleBody){
        Integer coupleId = coupleService.addCouple(coupleBody);
        return "커플Id:"+coupleId +":"+ coupleBody.getParticipant1Id()+"님과 "+coupleBody.getParticipant2Id()+"님의 커플 정보 입력이 완료되었습니다.";
    }

    @Operation(summary="커플 정보 확인")
    @GetMapping("/list")
    public List<CoupleEntity> getAllCouples(){
        return coupleService.getAllCouples();
    }
}
