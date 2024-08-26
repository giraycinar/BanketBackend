package com.bilgeadam.banket.mapper;
import com.bilgeadam.banket.dto.request.SaveAnswerRequestDto;
import com.bilgeadam.banket.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAnswerMapper {
    IAnswerMapper INSTANCE = Mappers.getMapper(IAnswerMapper.class);
   Answer saveAnswerDtoToAnswer(SaveAnswerRequestDto dto);
}

