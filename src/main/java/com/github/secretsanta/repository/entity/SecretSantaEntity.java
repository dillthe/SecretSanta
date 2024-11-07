package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "secret_santa")
public class SecretSantaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "santa_id")
    private Integer santaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giver_id", nullable = false)
    @JsonManagedReference
    private ParticipantEntity giver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonManagedReference
    private ParticipantEntity receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference
    private EventEntity event;

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public void setGiver(ParticipantEntity giver) {this.giver = giver;    }

    public void setReceiver(ParticipantEntity receiver) {
        this.receiver = receiver;
    }
}
