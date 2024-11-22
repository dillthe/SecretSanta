package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.service.SecretSantaService;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event/{eventId}")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    @Operation(summary="Assign Secret Santa by Event")
    @PostMapping("/secret-santa/assign")
    public String assignSecretSanta(@Valid @PathVariable int eventId) {
        secretSantaService.assignSecretSanta(eventId);
        return "Secret Santa is successfully assigned";
    }


    @Operation(summary="Secret Santa Lists by Event")
    @GetMapping("/secret-santas")
    public List<SecretSantaDTO> getSecretSantasByEvent(@Valid @PathVariable int eventId) {
        List<SecretSantaDTO> secretSantaDTOs= secretSantaService.getSecretSantasByEvent(eventId);
        return secretSantaDTOs;
    }


    @Operation(summary="Delete Secret Santa List by Event")
    @DeleteMapping("/secret-santas")
    public String deleteSecretSantasByEvent(@Valid @PathVariable int eventId){
        secretSantaService.deleteSecretSantasByEvent(eventId);
        return "Secret santa list" + "(Event Id:" + eventId +") is successfully deleted";
    }

    @Operation(summary="Send email notification for result")
    @PostMapping("/secret-santas/email")
    public String sendEmail(@Valid @PathVariable int eventId){
        secretSantaService.sendEmail(eventId);
        return "Secret Santa Assignments sent!";
    }
}