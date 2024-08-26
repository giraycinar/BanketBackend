package com.bilgeadam.banket.mapper;

import com.bilgeadam.banket.dto.request.AddQuestionToSurveyDto;
import com.bilgeadam.banket.dto.request.SaveSurveyRequestDto;
import com.bilgeadam.banket.entity.Question;
import com.bilgeadam.banket.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISurveyMapper {
    Survey surveyDtoToSurvey(SaveSurveyRequestDto dto);
    List<Question> questionDtosToQuestions(List<AddQuestionToSurveyDto> dto);
}