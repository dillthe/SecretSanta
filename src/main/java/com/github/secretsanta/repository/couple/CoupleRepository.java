package com.github.secretsanta.repository.couple;

import com.github.secretsanta.repository.entity.CoupleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoupleRepository extends JpaRepository<CoupleEntity, Integer> {
}
