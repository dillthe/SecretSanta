package com.github.secretsanta.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoupleBody {
    @NotNull(message = "Participant 1 ID must not be null")
    private Integer participant1Id;
    @NotNull(message = "Participant 2 ID must not be null")
    private Integer participant2Id;
    private int eventId;
}
