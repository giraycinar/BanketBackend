package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.constant.ValidationMessage;
import com.bilgeadam.banket.constant.ValidationRegex;
import jakarta.validation.constraints.Email;
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
public class UpdateUserRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    @Email
    private String email;


    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
