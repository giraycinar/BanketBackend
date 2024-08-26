package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveQuestionRequestDto {
    private String description;
    private QuestionType questionType;
    private String options;
}
