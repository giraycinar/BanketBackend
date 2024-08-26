package com.bilgeadam.banket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailSurveyDto {

    private List<String> groupNames;

    private UUID surveyUuid;

}
