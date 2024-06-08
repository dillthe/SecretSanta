package com.github.secretsanta.service;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.service.exceptions.NotAcceptException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SecretSantaService {

    public List<SecretSantaPair> drawNames(List<ParticipantEntity> participants) {
        if (participants.size() < 2) {
            throw new NotAcceptException("At least two participants are required");
        }

        List<ParticipantEntity> givers = new ArrayList<>(participants);
        List<ParticipantEntity> receivers = new ArrayList<>(participants);
        Collections.shuffle(receivers);


        while (hasAnyDuplicates(givers, receivers) || hasCoupleDuplicates(givers, receivers)) {
            Collections.shuffle(receivers);
        }

        List<SecretSantaPair> pairs = new ArrayList<>();
        for (int i = 0; i < givers.size(); i++) {
            pairs.add(new SecretSantaPair(givers.get(i).getParticipantName(), receivers.get(i).getParticipantName()));
            //get(i) 부분은 List 인터페이스에서 제공하는 메서드로, 리스트의 i번째 요소를 가져오는 메서드.
        }

        return pairs;
    }

    private boolean hasAnyDuplicates(List<ParticipantEntity> list1, List<ParticipantEntity> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getParticipantName().equals(list2.get(i).getParticipantName())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCoupleDuplicates(List<ParticipantEntity> list1, List<ParticipantEntity> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getCoupleEntity() != null && list2.get(i).getCoupleEntity() != null) {
                Integer coupleId1 = list1.get(i).getCoupleEntity().getCoupleId();
                Integer coupleId2 = list2.get(i).getCoupleEntity().getCoupleId();
                if (coupleId1 != null && coupleId1.equals(coupleId2)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    public static class SecretSantaPair {
        private String giver;
        private String receiver;
    }
}
