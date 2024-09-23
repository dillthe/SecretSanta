package com.github.secretsanta.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoupleDTO {
    private Integer coupleId;
    private Integer participant1Id;
    private String participant1Name;
    private Integer participant2Id;
    private String participant2Name;
    private Integer eventId;

    public void setParticipant1Id(Integer participant1Id) {
        this.participant1Id = participant1Id;
    }

    public void setParticipant2Id(Integer participant2Id) {
        this.participant2Id = participant2Id;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }



//    public void setParticipant1Id(Integer participant1Id) {
//        this.participant1Id = participant1Id;
//    }
//
//    public void setParticipant2Id(Integer participant2Id) {
//        this.participant2Id = participant2Id;
//    }
}