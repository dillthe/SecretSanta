package com.github.secretsanta.repository.couple;
import com.github.secretsanta.repository.entity.CoupleEntity;
import com.github.secretsanta.repository.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CoupleRepository extends JpaRepository<CoupleEntity, Integer> {
    Optional<CoupleEntity> findByParticipant1_ParticipantIdAndParticipant2_ParticipantId(Integer participant1Id, Integer participant2Id);
    Optional<CoupleEntity> findByParticipant2_ParticipantIdAndParticipant1_ParticipantId(Integer participant2Id, Integer participant1Id);

    List<CoupleEntity> findAllByEvent(EventEntity eventEntity);

}
