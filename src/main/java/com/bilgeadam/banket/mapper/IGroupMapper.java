package com.bilgeadam.banket.mapper;

import com.bilgeadam.banket.dto.request.SaveGroupRequestDto;
import com.bilgeadam.banket.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGroupMapper {

    IGroupMapper INSTANCE = Mappers.getMapper(IGroupMapper.class);

    Group saveGroupDtoToGroup(SaveGroupRequestDto dto);

}
