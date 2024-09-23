package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.service.SecretSantaService;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event/{eventId}")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    @PostMapping("/secret-santa/assign")
    public List<SecretSantaDTO> assignSecretSanta(@PathVariable int eventId) {
        return secretSantaService.assignSecretSanta(eventId);
    }

    @GetMapping("/secret-santas")
    public List<SecretSantaEntity> gedAllSecretSantas() {
        return secretSantaService.getAllSecretSantas();
    }

//    @DeleteMapping("/delete")
//    public SecretSantaEntity deleteAllSecretSantas(){
//        return secretSantaService.deleteAllSecretSantas();
//    }
}