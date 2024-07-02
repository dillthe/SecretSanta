package com.github.secretsanta.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.secretsanta.web.dto.ParticipantBody;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.List;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "participant")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Integer participantId;

    @NotBlank(message = "Participant name cannot be blank")
    @Pattern(regexp = "^[가-힣a-zA-Z\\s'-]+$", message = "Participant name should be valid")
    @Column(name = "participant_name", nullable = false)
    private String participantName;

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public void setParticipantBody(ParticipantBody participantBody) {
        this.participantName = participantBody.getParticipantName();
        this.email = participantBody.getEmail();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="couple_id")
    @JsonBackReference
    private CoupleEntity coupleEntity;

    @OneToMany(mappedBy = "participant1")
    @JsonBackReference
    private List<CoupleEntity> couplesAsFirstParticipant;

    @OneToMany(mappedBy = "participant2")
    @JsonBackReference
    private List<CoupleEntity> couplesAsSecondParticipant;

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }
}
