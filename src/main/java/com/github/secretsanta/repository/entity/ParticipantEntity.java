package com.github.secretsanta.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
