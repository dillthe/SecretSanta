package com.github.secretsanta.service;

import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.event.EventRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.EventMapper;
import com.github.secretsanta.service.mapper.ParticipantMapper;
import com.github.secretsanta.web.dto.EventBody;
import com.github.secretsanta.web.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;


    public Integer createEvent(EventBody eventBody) {
        boolean eventExists = eventRepository.findByEventName(eventBody.getEventName()).isPresent();
        if(eventExists){
            throw new NotAcceptException("이벤트 " + eventBody.getEventName() + "이(가) 이미 존재합니다." + eventBody.getEventDate() + "에 생성되었습니다."
                    +"찾으시는 이벤트가 아니라면 다른 이름으로 생성하세요.");
        }
        EventEntity eventEntity = EventMapper.INSTANCE.idAndEventBodyToEventEntity(null,eventBody);
        EventEntity eventCreated = eventRepository.save(eventEntity);

        return eventCreated.getEventId();
    }

    public EventDTO getEventById(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event not found with id " + eventId));
        EventDTO eventDTO = EventMapper.INSTANCE.eventEntityToEventDTO(eventEntity);
        return eventDTO;
    }
}
