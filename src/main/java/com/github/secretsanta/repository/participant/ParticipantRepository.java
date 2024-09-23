package com.github.secretsanta.repository.participant;

import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {
    Optional<ParticipantEntity> findByEmail(String email);

    List<ParticipantEntity> findAllByEvent(EventEntity eventEntity);

}
