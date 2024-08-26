package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveFilledSurveyRequestDto {

    private String userId;

    private String surveyId;

    private List<Answer> answers;

}
