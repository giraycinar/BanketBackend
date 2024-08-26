package com.bilgeadam.banket.mapper;

import com.bilgeadam.banket.dto.request.SaveUserRequestDto;
import com.bilgeadam.banket.dto.response.UserResponseDto;
import com.bilgeadam.banket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User saveUserDtoToUser(SaveUserRequestDto dto);


    UserResponseDto userToUserResponseDto(User user);
}
