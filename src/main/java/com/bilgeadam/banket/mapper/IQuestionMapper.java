package com.bilgeadam.banket.mapper;


import com.bilgeadam.banket.dto.request.SaveQuestionRequestDto;
import com.bilgeadam.banket.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IQuestionMapper {

    IQuestionMapper INSTANCE = Mappers.getMapper(IQuestionMapper.class);

    Question saveQuestionDtoToQuestion(SaveQuestionRequestDto dto);
}
