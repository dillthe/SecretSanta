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
public class SecretSantaDTO {
    private Integer santaId;
    private Integer giverId;
    private Integer receiverId;
    private int eventId;

//    public void setGiverId(Integer giverId) {
//        this.giverId = giverId;
//    }
//
//    public void setReceiverId(Integer receiverId) {
//        this.receiverId = receiverId;
//    }
}
