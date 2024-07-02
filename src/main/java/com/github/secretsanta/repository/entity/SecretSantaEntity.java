package com.github.secretsanta.repository.entity;

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
    private Integer santaId;

    @ManyToOne
    @JoinColumn(name = "giver_id", nullable = false)
    private ParticipantEntity giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private ParticipantEntity receiver;

    public void setGiver(ParticipantEntity giverId) {
        this.giver = giverId;
    }

    public void setReceiver(ParticipantEntity receiverId) {
        this.receiver = receiverId;
    }
}
