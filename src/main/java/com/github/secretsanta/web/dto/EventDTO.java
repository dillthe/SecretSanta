package com.github.secretsanta.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EventDTO {
    private int eventId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private List<ParticipantDTO> participants;
    private List<CoupleDTO> couples;
    private List<SecretSantaDTO> secretSantas;

    //이건 보안의 문제로 보완의 필요성이 있음.
    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public void setCouples(List<CoupleDTO> couples) {
        this.couples = couples;
    }

    public void setSecretSantas(List<SecretSantaDTO> secretSantas) {
        this.secretSantas = secretSantas;
    }
}
