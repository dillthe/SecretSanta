package com.github.secretsanta.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SecretSantaBody {
    private Integer giverId;
    private Integer receiverId;

//    public void setGiverId(Integer participantId) {
//        this.giverId=participantId;
//    }
//    public void setReceiverId(Integer participantId) {
//        this.receiverId=participantId;
//    }
}
