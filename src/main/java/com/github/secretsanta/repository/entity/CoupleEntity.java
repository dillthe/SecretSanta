package com.github.secretsanta.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "couple")
public class CoupleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couple_id")
    private Integer coupleId;

    @ManyToOne
    @JoinColumn(name = "participant1_id", nullable = false)
    private ParticipantEntity participant1;

    @ManyToOne
    @JoinColumn(name = "participant2_id", nullable = false)
    private ParticipantEntity participant2;
}
