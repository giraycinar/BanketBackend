package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateQuestionRequestDto {
    private UUID uuid;
    private String description;
    private QuestionType questionType;
    private String options;
}
