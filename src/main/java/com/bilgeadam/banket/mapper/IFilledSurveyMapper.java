package com.bilgeadam.banket.mapper;

import com.bilgeadam.banket.dto.request.SaveFilledSurveyRequestDto;
import com.bilgeadam.banket.dto.response.AnswerDto;
import com.bilgeadam.banket.entity.Answer;
import com.bilgeadam.banket.entity.FilledSurvey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IFilledSurveyMapper {

    IFilledSurveyMapper INSTANCE = Mappers.getMapper(IFilledSurveyMapper.class);


    FilledSurvey saveFilledSurveyRequestDtoToEntity(SaveFilledSurveyRequestDto dto);


    List<AnswerDto> answerListToAnswerDtoList(List<Answer> answers);
}
