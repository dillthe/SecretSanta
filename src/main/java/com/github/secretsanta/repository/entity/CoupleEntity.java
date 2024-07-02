package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "couple")
public class CoupleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couple_id")
    private Integer coupleId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "participant1_id", nullable = false)
    @JsonManagedReference
    private ParticipantEntity participant1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "participant2_id", nullable = false)
    @JsonManagedReference
    private ParticipantEntity participant2;

    public void setParticipant1(ParticipantEntity participant1) {
        this.participant1 = participant1;
    }

    public void setParticipant2(ParticipantEntity participant2) {
        this.participant2 = participant2;
    }
}
