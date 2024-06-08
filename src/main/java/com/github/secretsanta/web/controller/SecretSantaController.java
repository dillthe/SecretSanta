package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.service.SecretSantaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secret-santa")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    @PostMapping("/draw")
    public List<SecretSantaService.SecretSantaPair> drawNames(@RequestBody List<ParticipantEntity> participants) {
                return secretSantaService.drawNames(participants);
    }
}