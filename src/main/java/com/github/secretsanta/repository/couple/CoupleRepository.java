//package com.github.secretsanta.repository.couple;
//
////import com.github.secretsanta.repository.entity.CoupleEntity;
//import com.github.secretsanta.repository.entity.CoupleEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface CoupleRepository extends JpaRepository<CoupleEntity, Integer> {
//    Optional<Object> findByParticipants(Integer participant1Id, Integer participant2Id);
//
//
////    @Query("SELECT c FROM CoupleEntity c WHERE (c.participant1.participantId = :participant1Id AND c.participant2.participantId = :participant2Id) " +
////            "OR(c.participant1.participantId = :participant2Id AND c.participant2.participantId = :participant1Id")
////    Optional<CoupleEntity> findByParticipants(@Param("participant1Id") Integer participant1Id, @Param("participant2Id") Integer participant2Id);
//}
