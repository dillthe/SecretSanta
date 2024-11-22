package com.github.secretsanta.service;

import com.github.secretsanta.repository.couple.CoupleRepository;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.repository.event.EventRepository;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.repository.secretSanta.SecretSantaRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.exceptions.NotFoundException;
import com.github.secretsanta.service.mapper.SecretSantaMapper;
import com.github.secretsanta.web.dto.SecretSantaBody;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;
    private final CoupleRepository coupleRepository;
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final JavaMailSender mailSender;


    @Transactional
    public List<SecretSantaDTO> assignSecretSanta(int eventId) {

        if (secretSantaRepository.existsByEvent_EventId(eventId)) {
            throw new NotAcceptException("Secret Santa list already exists.");
        }
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(()->new NotFoundException("Event Not Found"));

        List<ParticipantEntity> participants = event.getParticipants();
        List<CoupleEntity> couples = event.getCouples();
        if (participants.size() < 2) {
            throw new NotAcceptException("At least two participants are required");
        }

        List<ParticipantEntity> givers = new ArrayList<>(participants);
        List<ParticipantEntity> receivers = new ArrayList<>(participants);
        Collections.shuffle(receivers);


        //while문이 true이면(즉 커플이든, 시크릿산타든 중복이 있으면) receiver shuffle을 계속 한다.
        while (hasAnyDuplicates(givers, receivers) || hasCoupleDuplicates(givers, receivers, couples)) {
            Collections.shuffle(receivers);
        }

        List<SecretSantaDTO> secretSantaDTOs = new ArrayList<>();
        for (int i = 0; i < givers.size(); i++) {
            SecretSantaBody secretSantaBody = new SecretSantaBody();
            secretSantaBody.setGiverId(givers.get(i).getParticipantId());
            secretSantaBody.setReceiverId(receivers.get(i).getParticipantId());
            secretSantaBody.setEventId(eventId);
            //get(i) 부분은 List 인터페이스에서 제공하는 메서드로, 리스트의 i번째 요소를 가져오는 메서드.

            SecretSantaEntity secretSantaEntity = SecretSantaMapper.INSTANCE.idAndSecretSantaBodytoSecretSantaEntity(null, secretSantaBody);
            SecretSantaEntity secretSantaCreated = secretSantaRepository.save(secretSantaEntity);

            SecretSantaDTO secretSantaDTO = SecretSantaMapper.INSTANCE.secretSantaEntityToSecretSantaDTO(secretSantaCreated);
            secretSantaDTOs.add(secretSantaDTO);

        }  return secretSantaDTOs;
    }

    private boolean hasAnyDuplicates(List<ParticipantEntity> givers, List<ParticipantEntity> receivers) {
        for (int i = 0; i < givers.size(); i++) {
            if (givers.get(i).getParticipantName().equals(receivers.get(i).getParticipantName())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCoupleDuplicates(List<ParticipantEntity> givers, List<ParticipantEntity> receivers, List<CoupleEntity> couples) {
        for (int i = 0; i < givers.size(); i++) {
            ParticipantEntity giver = givers.get(i);
            ParticipantEntity receiver = receivers.get(i);
            for(CoupleEntity couple:couples) {
                if ((couple.getParticipant1().equals(giver) && couple.getParticipant2().equals(receiver)) ||
                        (couple.getParticipant1().equals(receiver) && couple.getParticipant2().equals(giver))) {
                    return true;
                }
            }
        } return false;
    }

//    public List<SecretSantaEntity> getAllSecretSantas() {
//        return secretSantaRepository.findAll();
//    }

    public List<SecretSantaDTO> getSecretSantasByEvent(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(()-> new NotFoundException("Event not found with EventId: " + eventId));
        List<SecretSantaEntity> secretSantaEntities = secretSantaRepository.findAllByEvent(eventEntity);
        List<SecretSantaDTO> secretSantaDTOs = SecretSantaMapper.INSTANCE.secretSantaEntitiesToSecretSantaDTOs(secretSantaEntities);
        return secretSantaDTOs;

    }

    @Transactional
    public void deleteSecretSantasByEvent(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(()-> new NotFoundException("Event not found with EventId: " + eventId));
        List<SecretSantaEntity> secretSantaList = secretSantaRepository.findAllByEvent(eventEntity);
        if (secretSantaList.isEmpty()) {
            // Secret Santa 리스트가 비어있으면 예외를 던지거나 메시지 반환
            throw new NotFoundException("No Secret Santa list found for eventId: " + eventId);
        }
        secretSantaRepository.deleteAllByEvent(eventEntity);
    }

    public void sendEmail(int eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found with EventId: " + eventId));
        List<SecretSantaEntity> secretSantaList = secretSantaRepository.findAllByEvent(eventEntity);

        for (SecretSantaEntity secretSanta : secretSantaList) {
            String giver = secretSanta.getGiver().getParticipantName();
            String receiver = secretSanta.getReceiver().getParticipantName();
            String toEmail = secretSanta.getGiver().getEmail();
            String eventName = secretSanta.getEvent().getEventName();

            sendEmailToParticipant(giver, toEmail, receiver, eventName);

        }
    }
        private void sendEmailToParticipant(String giver, String toEmail, String receiver, String eventName){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject( eventName + " Assignment");
        message.setText("Hello " + giver + ",\n\nYou have been assigned to give a gift to " + receiver +
                "!\n\n Happy Holidays!");

        mailSender.send(message);
    }


//    public SecretSantaEntity deleteAllSecretSantas() {
//        return secretSantaRepository.deleteAll();
//    }
    //시크릿산타 목록들을 이벤트로 묶어서 코드 다시 짜기!
    //재밌다!!!!!
    //그리고 이벤트가 실행되면(시크릿산타가 정해지면) giver들에게 알림 이메일이 가도록 하기!

}
