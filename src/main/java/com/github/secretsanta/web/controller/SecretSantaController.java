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
@RequestMapping("/api/secret-santa")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    @PostMapping("/draw")
    public List<SecretSantaDTO> drawNames() {
        return secretSantaService.drawNames();
    }

    @GetMapping("/list")
    public List<SecretSantaEntity> gedAllSecretSantas() {
        return secretSantaService.getAllSecretSantas();
    }
}