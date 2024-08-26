package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.constant.ValidationMessage;
import com.bilgeadam.banket.constant.ValidationRegex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdatePasswordRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = ValidationMessage.PASSWORD_REGEX_ERROR_MESSAGE)
    private String password;

}
