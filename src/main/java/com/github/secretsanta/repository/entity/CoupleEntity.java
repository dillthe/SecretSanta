package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
        private int coupleId;

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "participant1_id", nullable = false)
        @JsonManagedReference
        private ParticipantEntity participant1;

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "participant2_id", nullable = false)
        @JsonManagedReference
        private ParticipantEntity participant2;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id", nullable = false)
        @JsonBackReference
        private EventEntity event;

        public void setParticipant1(ParticipantEntity participant1) {
            this.participant1 = participant1;
        }

        public void setParticipant2(ParticipantEntity participant2) {
            this.participant2 = participant2;
        }

        public void setEvent(EventEntity event) {
            this.event = event;
        }

}
