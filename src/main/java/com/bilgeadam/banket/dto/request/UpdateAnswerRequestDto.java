package com.bilgeadam.banket.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateAnswerRequestDto {
    private String answerText;
    private String questionUuid;
    private String filledSurveyUuid;
}
