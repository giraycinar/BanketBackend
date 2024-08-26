package com.bilgeadam.banket.dto.request;

import java.util.UUID;

public class DeleteQuestionRequestDto {

    private UUID surveyUuid;
    private String questionId;


    public DeleteQuestionRequestDto() {}

    public DeleteQuestionRequestDto(UUID surveyUuid, String questionId) {
        this.surveyUuid = surveyUuid;
        this.questionId = questionId;
    }

    public UUID getSurveyUuid() {
        return surveyUuid;
    }

    public void setSurveyUuid(UUID surveyUuid) {
        this.surveyUuid = surveyUuid;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
