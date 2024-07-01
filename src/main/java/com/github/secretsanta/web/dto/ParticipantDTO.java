package com.github.secretsanta.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ParticipantDTO {
        @Schema(description = "Participant Id", example = "1")
        private Integer participantId;

        @Schema(description = "Participant Name", example = "John Chris, 박나래 : 영문 및 한글 이름 입력 가능")
        @NotBlank(message = "Participant name cannot be blank")
        @Pattern(regexp = "^[가-힣a-zA-Z\\s'-]+$", message = "Participant name should be valid")
        private String participantName;

        @Schema(description = "Email", example = "abc@gmail.com, 이메일 형식으로 입력해주세요")
        @NotBlank(message = "Email cannot be blank")
//    @Email(message = "Email should be valid")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email should be valid")
        private String email;
}
