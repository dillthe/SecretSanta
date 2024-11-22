package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "event_id")
        private int eventId;

        @Column(name = "event_name")
        private String eventName;

        @Column(name = "event_description")
        private String eventDescription;

        @Column(name = "event_date")
        private Date eventDate;

        @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<ParticipantEntity> participants;

        @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
        @JsonManagedReference
        private List<CoupleEntity> couples;

        @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
        @JsonManagedReference
        private List<SecretSantaEntity> secretSantas;

        public void setEventId(int eventId) {
                this.eventId = eventId;
        }

        public void setParticipants(List<ParticipantEntity> participants) {
                this.participants = participants;
        }

        public void setCouples(List<CoupleEntity> couples) {
                this.couples = couples;
        }

        public void setSecretSantas(List<SecretSantaEntity> secretSantas) {
                this.secretSantas = secretSantas;
        }

        public void setEventName(String eventName) {
                this.eventName = eventName;
        }

        public void setEventDescription(String eventDescription) {
                this.eventDescription = eventDescription;
        }

        public void setEventDate(Date eventDate) {
                this.eventDate = eventDate;
        }
}
