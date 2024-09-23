package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "secret_santa")
public class SecretSantaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "santa_id")
    private int santaId;

    @ManyToOne
    @JoinColumn(name = "giver_id", nullable = false)
    private ParticipantEntity giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private ParticipantEntity receiver;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference
    private EventEntity event;

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public void setGiver(ParticipantEntity giverId) {
        this.giver = giverId;
    }

    public void setReceiver(ParticipantEntity receiverId) {
        this.receiver = receiverId;
    }
}
