package com.github.secretsanta.repository.event;

import com.github.secretsanta.repository.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface EventRepository extends JpaRepository<EventEntity, Integer> {
//    boolean findByEventName(String name);
    Optional<EventEntity> findByEventName(String name);
}

