package com.bilgeadam.banket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AgainStudentListMailSurveyDto {

    private List<String> emailList;
    private String surveyId;
}
