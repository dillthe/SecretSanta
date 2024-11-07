package com.github.secretsanta.repository.secretSanta;

import com.github.secretsanta.repository.entity.EventEntity;
import com.github.secretsanta.repository.entity.ParticipantEntity;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretSantaRepository extends JpaRepository<SecretSantaEntity, Integer> {

    boolean existsByEvent_EventId(int eventId);

    List<SecretSantaEntity> findAllByEvent(EventEntity eventEntity);

    List<SecretSantaEntity> deleteAllByEvent(EventEntity eventEntity);
}
