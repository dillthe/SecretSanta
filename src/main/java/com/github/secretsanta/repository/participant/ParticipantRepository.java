package com.github.secretsanta.repository.participant;

import com.github.secretsanta.repository.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {
    Optional<Object> findByEmail(String email);
}
