package com.bilgeadam.banket.mapper;

import com.bilgeadam.banket.dto.request.SaveStudentRequestDto;
import com.bilgeadam.banket.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IStudentMapper {

    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);

    Student saveStudentRequestDtoToStudent(SaveStudentRequestDto dto);
}
