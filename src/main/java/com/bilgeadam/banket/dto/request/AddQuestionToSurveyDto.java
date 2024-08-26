package com.bilgeadam.banket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddQuestionToSurveyDto {
    String questionId;
    boolean isRequired;
}
