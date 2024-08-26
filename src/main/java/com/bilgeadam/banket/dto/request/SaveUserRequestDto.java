package com.bilgeadam.banket.dto.request;


import com.bilgeadam.banket.constant.ValidationMessage;
import com.bilgeadam.banket.constant.ValidationRegex;
import com.bilgeadam.banket.entity.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveUserRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = ValidationMessage.PASSWORD_REGEX_ERROR_MESSAGE)
    private String password;

    @NotBlank
    private String rePassword;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private List<ERole> roles;

}
