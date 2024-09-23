package com.github.secretsanta.web.controller;

import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.service.EventService;
import com.github.secretsanta.web.dto.EventBody;
import com.github.secretsanta.web.dto.EventDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;

    @Operation(summary="create an event")
    @PostMapping
    public String createEvent(@RequestBody EventBody eventBody) {
        Integer eventId = eventService.createEvent(eventBody);
        return "이벤트id:" + eventId+ "가 생성되었습니다.";
    }

    @Operation(summary="find event info by event Id")
    @GetMapping("/{eventId}")
    public EventDTO getEvent(@PathVariable int eventId) {
        EventDTO eventDTO = eventService.getEventById(eventId);
        return eventDTO;
    }

}
