package com.github.secretsanta.repository.secretSanta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.secretsanta.repository.entity.SecretSantaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretSantaRepository extends JpaRepository<SecretSantaEntity, Integer> {
}
