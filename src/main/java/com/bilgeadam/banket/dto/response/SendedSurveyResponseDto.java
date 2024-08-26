package com.bilgeadam.banket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SendedSurveyResponseDto {
    UUID uuid;
    UUID groupUuid;
    String surveyId;
    String groupName;
    String surveyName;
    LocalDateTime sendedAt;


}
