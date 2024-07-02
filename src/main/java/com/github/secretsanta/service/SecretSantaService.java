package com.github.secretsanta.service;

import com.github.secretsanta.repository.couple.CoupleRepository;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import com.github.secretsanta.repository.participant.ParticipantRepository;
import com.github.secretsanta.repository.secretSanta.SecretSantaRepository;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import com.github.secretsanta.service.mapper.SecretSantaMapper;
import com.github.secretsanta.web.dto.SecretSantaBody;
import com.github.secretsanta.web.dto.SecretSantaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;
    private final CoupleRepository coupleRepository;
    private final ParticipantRepository participantRepository;

    public List<SecretSantaDTO> drawNames() {

        List<ParticipantEntity> participants = participantRepository.findAll();
        List<CoupleEntity> couples = coupleRepository.findAll();
        if (participants.size() < 2) {
            throw new NotAcceptException("At least two participants are required");
        }

        if (secretSantaRepository.count() > 0) {
            throw new NotAcceptException("Secret Santa list already exists.");
//            return new ArrayList<>();  // 이미 매칭된 경우 빈 리스트 반환
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

    public List<SecretSantaEntity> getAllSecretSantas() {
        return secretSantaRepository.findAll();
    }

//    public SecretSantaEntity deleteAllSecretSantas() {
//        return secretSantaRepository.deleteAll();
//    }
    //시크릿산타 목록들을 이벤트로 묶어서 코드 다시 짜기!
    //재밌다!!!!!
    //그리고 이벤트가 실행되면(시크릿산타가 정해지면) giver들에게 알림 이메일이 가도록 하기!

}
