package com.bilgeadam.banket.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateSurveyRequestDto {
    private UUID uuid;
    private String name;
    private List<AddQuestionToSurveyDto> questions;

}
